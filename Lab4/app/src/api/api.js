const axios = require("axios").default;
const API_URL = "http://localhost:8080/api";

export async function getUsers() {
  return {
    users: ["admin", "Johny", "Vincent", "Mr. Steele"],
  };

  //   console.log(`Fetching users from ${API_URL}`);
  //   let res = await axios
  //     .get(`${API_URL}/users`, {
  //       params: {},
  //     })
  //     .then(response => {
  //       return response;
  //     });

  //     return res.data;
}

export async function getUser(username) {
  return {
      name: "Johny",
      surname: "Krasinski",
      email: "johny.kra@gmail.com",
  };

  // console.log(`Fetching users from ${API_URL}`);
  // let res = await axios
  //   .get(`${API_URL}/users`, {
  //     params: {},
  //   })
  //   .then((response) => {
  //     return response;
  //   });

  // return res.data;
}

export async function getUserCars(user) {
  return {
    cars: [
      {
        id: 2,
        name: "Audi RSQ8",
      },
      {
        id: 3,
        name: "Opel Insignia",
      },
      {
        id: 6,
        name: "Skoda Fabia",
      },
    ],
  };
  // console.log(`Fetching users from ${API_URL}`);
  // let res = await axios
  //   .get(`${API_URL}/users/${user}/cars`, {
  //     params: {},
  //   })
  //   .then(response => {
  //     return response;
  //   });

  //   return res.data;
}

export async function deleteUser(user) {
  let res = await axios
    .delete(`${API_URL}/users/${user}`, {
      params: {},
    })
    .then((response) => {
      return response;
    });

  return res.data;
}

const api = {
  getUsers,
  getUser,
  getUserCars,
  deleteUser,
};

export default api;
