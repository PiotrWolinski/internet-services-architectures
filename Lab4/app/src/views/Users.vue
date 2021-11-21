<template>
  <b-container>
    <b-row>
      <b-col cols="5" />
      <b-col cols="2">
        <b-button variant="success" v-b-modal.user-modal>Create</b-button>
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
          <template #cell(details)="row">
            <b-button variant="secondary" @click="viewUser(row)">{{
              row.item._showDetails ? "Hide" : "Show"
            }}</b-button>
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
              <b-row class="mb-2">
                <Cars :detailed="true" :user="row.item.name" />
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
    <b-modal id="user-modal" size="lg" :hide-header="true" :hide-footer="true">
      <b-container>
        <h4>Create new user form</h4>
        <hr />
        <b-row class="pt-2">
          <b-col cols="3">
            <b>Login</b>
          </b-col>
          <b-col cols="3">
            <b-form-input v-model="form.login" placeholder="Login" />
          </b-col>
          <b-col cols="3">
            <b>Password</b>
          </b-col>
          <b-col cols="3">
            <b-form-input v-model="form.password" :type="'password'" placeholder="Password" />
          </b-col>
        </b-row>
        <b-row class="pt-4">
          <b-col cols="3">
            <b>Name</b>
          </b-col>
          <b-col cols="3">
            <b-form-input v-model="form.name" placeholder="Name" />
          </b-col>
          <b-col cols="3">
            <b>Surname</b>
          </b-col>
          <b-col cols="3">
            <b-form-input v-model="form.surname" placeholder="Surname" />
          </b-col>
        </b-row>
        <b-row class="pt-4">
          <b-col cols="3">
            <b>Email</b>
          </b-col>
          <b-col cols="3">
            <b-form-input v-model="form.email" :type="'email'" placeholder="Email" />
          </b-col>
          <b-col cols="3">
            <b>Birthdate</b>
          </b-col>
          <b-col cols="3">
            <b-form-input v-model="form.birthdate" placeholder="YYYY-MM-DD" />
          </b-col>
        </b-row>
      </b-container>
    </b-modal>
  </b-container>
</template>

<script>
import { getUsers, getUser, deleteUser as apiDeleteUser } from "@/api/api.js";
import Cars from "@/components/Cars.vue";

const FIELDS = ["name", "details", "edit", "delete"];

export default {
  name: "Users",
  components: {
    Cars,
  },
  data: function () {
    return {
      users: [],
      usersDetailed: {},
      items: [],
      fields: FIELDS,
      selectedUser: "",
      usersLoaded: false,
      userLoading: false,
      form: {
        name: null,
        surname: null,
        login: null,
        password: null,
        email: null,
        birthdate: null,
      },
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
        this.items.push({
          id: id,
          name: user,
          detailsLoaded: false,
          cars: [],
        });
        id++;
      }
    },

    viewUser(row) {
      this.loadUser(row.item);
      row.toggleDetails();
    },

    editUser(userName) {
      console.log("Editing ", userName);
    },

    async deleteUser(userName) {
      await apiDeleteUser(userName);
      this.loadUsers();
    },

    async loadUser(userObject) {
      userObject.details = await getUser(userObject.name);
      userObject.detailsLoaded = true;
    },

    resetForm() {
      for (let field in this.form) {
        field = null;
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
