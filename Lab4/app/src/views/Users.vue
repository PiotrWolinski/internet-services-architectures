<template>
  <div>
    <b-container>
      <b-row>
        <b-col cols="5" />
        <b-col cols="2">
          <b-button variant="success">Create</b-button>
        </b-col>
        <b-col cols="5" />
      </b-row>
      <b-row v-if="usersLoaded">
        <b-col cols="3"></b-col>
        <b-col>
          <b-table cols="6" v-if="usersLoaded" :fields="fields" :items="items">
            <template #cell(name)="data">
              {{ data.item.name }}
            </template>
            <template #cell(view)="row">
              <b-button
                variant="secondary"
                @click="
                  row.toggleDetails();
                  loadUser(row.item);
                "
                >View</b-button
              >
            </template>
            <template #cell(edit)="data">
              <b-button variant="warning" @click="editUser(data.item.name)"
                >Edit</b-button
              >
            </template>
            <template #cell(delete)="data">
              <b-button variant="danger" @click="deleteUser(data.item.name)">
                Delete
              </b-button>
            </template>
            <template #row-details="row">
              <b-container v-if="row.item.detailsLoaded">
                <b-row class="mb-2">
                  <b-col sm="3" class="text-sm-right"><b>Name:</b></b-col>
                  <b-col cols="3">{{ row.item.details.name }}</b-col>
                                    <b-col sm="3" class="text-sm-right"><b>Surname:</b></b-col>
                  <b-col cols="3">{{ row.item.details.surname }}</b-col>
                </b-row>
                <b-row class="mb-2">
                  <b-col sm="3" class="text-sm-right"><b>Email:</b></b-col>
                  <b-col cols="3">{{ row.item.details.email }}</b-col>
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
      <b-row v-else>
        <b-col cols="12" class="py-4">
          <b-spinner style="width: 3rem; height: 3rem" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { getUsers, getUser, deleteUser as apiDeleteUser } from "@/api/api.js";
const FIELDS = ["name", "view", "edit", "delete"];

export default {
  name: "Users",
  data: function () {
    return {
      users: [],
      usersDetailed: {},
      items: [],
      fields: FIELDS,
      selectedUser: "",
      usersLoaded: false,
      userLoading: false,
    };
  },
  async mounted() {
    this.loadUsers();
  },
  methods: {
    async loadUsers() {
      this.usersLoaded = false;
      this.users = [];

      let res = await getUsers();
      this.users = res.users;

      this.parseUsers();

      this.usersLoaded = true;
    },

    parseUsers() {
      this.items = [];
      let id = 0;
      for (const user of this.users) {
        this.items.push({ id: id, name: user, detailsLoaded: false });
        id++;
      }
    },

    viewUser(userName) {
      console.log("Viewing ", userName);
      this.selectedUser = userName;
    },

    editUser(userName) {
      console.log("Editing ", userName);
    },

    async deleteUser(userName) {
      await apiDeleteUser(userName);
      this.loadUsers();
    },

    async loadUser(userObject) {
      console.log("Loading");
      console.log(userObject);

      userObject.details = await getUser(userObject.name);
      console.log(userObject);

      userObject.detailsLoaded = true;
    },

    async loadUserCars() {
      
    }
  },
};
</script>

<style lang="scss" scoped></style>
