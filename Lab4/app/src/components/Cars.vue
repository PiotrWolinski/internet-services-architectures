<template>
  <b-container>
    <b-row v-if="!detailed">
      <b-col cols="5" />
      <b-col cols="2">
        <b-button variant="success">Create</b-button>
      </b-col>
      <b-col cols="5" />
    </b-row>
    <b-row v-if="!detailed && carsLoaded">
      <b-col cols="3"></b-col>
      <b-col>
        <b-table
          cols="6"
          v-if="carsLoaded"
          :fields="detailed_fields"
          :items="cars"
        >
          <template #cell(name)="data">
            {{ data.item.name }}
          </template>
          <template #cell(details)="row">
            <b-button variant="secondary" @click="viewCar(row)">{{
              row.item._showDetails ? "Hide" : "Show"
            }}</b-button>
          </template>
          <template #cell(edit)="data">
            <b-button variant="warning" @click="editCar(data.item.name)"
              >Edit</b-button
            >
          </template>
          <template #cell(delete)="data">
            <b-button variant="danger" @click="deleteCar(data.item.name)">
              Delete
            </b-button>
          </template>
          <template #row-details="row">
            <b-container v-if="row.item.detailsLoaded">
              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>Max speed:</b></b-col>
                <b-col cols="3">{{ row.item.details.maxSpeed }}</b-col>
                <b-col sm="3" class="text-sm-right"><b>Seats:</b></b-col>
                <b-col cols="3">{{ row.item.details.seats }}</b-col>
              </b-row>
              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>Wheels:</b></b-col>
                <b-col cols="3">{{ row.item.details.wheels }}</b-col>
                <b-col sm="3" class="text-sm-right"><b>Doors:</b></b-col>
                <b-col cols="3">{{ row.item.details.doors }}</b-col>
              </b-row>
              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>User:</b></b-col>
                <b-col cols="3">{{ row.item.details.user }}</b-col>
              </b-row>
            </b-container>
            <b-container v-else>
              <b-col cols="12" class="py-4">
                <b-spinner style="width: 3rem; height: 3rem" />
              </b-col>
            </b-container>
          </template>
        </b-table>
      </b-col>
      <b-col cols="3"></b-col>
    </b-row>
    <b-row v-if="detailed && carsLoadedDetailed">
      <b-col>
        <b-table cols="6" :fields="general_fields" :items="cars">
          <template #cell(max_speed)="data">
            {{ data.item.details.maxSpeed }}
          </template>
          <template #cell(seats)="data">
            {{ data.item.details.seats }}
          </template>
          <template #cell(wheels)="data">
            {{ data.item.details.wheels }}
          </template>
          <template #cell(delete)="data">
            <b-button variant="danger" @click="deleteCar(data.item.name)">
              Delete
            </b-button>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {
  getCars,
  getCar,
  getUserCars,
  deleteCar as apiDeleteCar,
} from "@/api/api.js";

const DETAILED_FIELDS = ["name", "details", "edit", "delete"];

const GENERAL_FIELDS = ["name", "max_speed", "seats", "wheels", "delete"];

export default {
  name: "Cars",

  props: {
    detailed: Boolean,
    user: String,
  },

  data: function () {
    return {
      cars: [],
      general_fields: GENERAL_FIELDS,
      detailed_fields: DETAILED_FIELDS,
      selectedCar: "",
      carsLoaded: false,
      carsLoadedDetailed: false,
      carLoading: false,
    };
  },
  async mounted() {
    await this.loadCars();
    await this.loadCarsDetailed();
    console.log(this.cars);
  },
  methods: {
    async loadCars() {
      this.carsLoaded = false;
      this.cars = [];

      let res = null;
      if (this.detailed) {
        res = await getUserCars(this.user);
      } else {
        res = await getCars();
      }

      console.log(res);

      this.cars = res.cars;

      this.parseCars();

      this.carsLoaded = true;
    },

    parseCars() {
      for (let car of this.cars) {
        car.detailsLoaded = false;
      }
    },

    viewCar(row) {
      this.loadCarDetails(row.item);
      row.toggleDetails();
    },

    editCar(carName) {
      console.log("Editing ", carName);
    },

    async deleteCar(carName) {
      await apiDeleteCar(carName);
      this.loadCars();
    },

    async loadCarDetails(carObject) {
      console.log("Loading");
      console.log(carObject);

      carObject.details = await getCar(carObject.id);
      console.log(carObject);

      carObject.detailsLoaded = true;
    },

    async loadCarsDetailed() {
      this.carsLoadedDetailed = false;
      for (let car of this.cars) {
        console.log(car);
        await this.loadCarDetails(car);
      }
      this.carsLoadedDetailed = true;
    },
  },
};
</script>

<style lang="scss" scoped></style>
