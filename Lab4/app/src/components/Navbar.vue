<template>
  <b-button-group>
    <b-button
      v-for="pathElement in PATHS"
      :key="pathElement.path"
      :variant="pathElement.active ? 'warning' : 'light'"
      @click="changeView(pathElement)"
    >
      {{ pathElement.name }}
    </b-button>
  </b-button-group>
</template>

<script>
const PATHS = [
  {
    path: "/",
    name: "Home",
    active: true,
  },
  {
    path: "/show_category",
    name: "Show category",
    active: false,
  },
  {
    path: "/add_category",
    name: "Add category",
    active: false,
  },
  {
    path: "/edit_category",
    name: "Edit category",
    active: false,
  },
  {
    path: "/add_element",
    name: "Add element",
    active: false,
  },
  {
    path: "/edit_element",
    name: "Edit element",
    active: false,
  },
  {
    path: "/element",
    name: "Show element",
    active: false,
  },
];

export default {
  name: "Navbar",

  data: function () {
    return {
      PATHS,
    };
  },

  methods: {
    changeView(pathElement) {
      for (let path of this.PATHS) {
        path.active = false;
      }
      pathElement.active = true;
      this.$router.push(pathElement.path);
      console.log(this.$Api);
    },
  },
};
</script>
