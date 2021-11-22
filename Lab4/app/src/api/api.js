const axios = require("axios").default;
const API_URL = "http://localhost:8080/api";

export async function getUsers() {
  // return {
  //   users: ["admin", "Johny", "Vincent", "Mr. Steele"],
  // };

  console.log(`Fetching users from ${API_URL}`);
  let res = await axios
    .get(`${API_URL}/users`, {
      params: {},
    })
    .then((response) => {
      return response;
    });

  return res.data;
}

export async function getUser(username) {
  // return {
  //   name: "Johny",
  //   surname: "Krasinski",
  //   email: "johny.kra@gmail.com",
  // };

  console.log(`Fetching one user from ${API_URL}`);
  let res = await axios
    .get(`${API_URL}/users/${username}`, {
      params: {},
    })
    .then((response) => {
      return response;
    });

  return res.data;
}

export async function getUserCars(user) {
  // return {
  //   cars: [
  //     {
  //       id: 2,
  //       name: "Audi RSQ8",
  //     },
  //     {
  //       id: 3,
  //       name: "Opel Insignia",
  //     },
  //     {
  //       id: 6,
  //       name: "Skoda Fabia",
  //     },
  //   ],
  // };
  console.log(`Fetching user cars from ${API_URL}`);
  let res = await axios
    .get(`${API_URL}/users/${user}/cars`, {
      params: {},
    })
    .then((response) => {
      return response;
    });

  return res.data;
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

export async function createUser(user) {
  let res = await axios
    .post(
      `${API_URL}/users`,
      {
        login: user.login,
        name: user.name,
        surname: user.surname,
        email: user.email,
        password: user.password,
        birthdate: user.birthdate,
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
    .then((response) => {
      return response;
    });

  return res;
}

export async function editUser(user) {
  let res = await axios
    .put(
      `${API_URL}/users/${user.login}`,
      {
        name: user.name,
        surname: user.surname,
        email: user.email,
        password: user.password,
        birthdate: user.birthdate,
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
    .then((response) => {
      return response;
    });

  return res;
}

export async function getCars() {
  return {
    cars: [
      {
        id: 1,
        name: "Audi A6",
      },
      {
        id: 2,
        name: "Audi RSQ8",
      },
      {
        id: 3,
        name: "Opel Insignia",
      },
      {
        id: 4,
        name: "Skoda Fabia",
      },
    ],
  };

  //   console.log(`Fetching cars from ${API_URL}`);
  //   let res = await axios
  //     .get(`${API_URL}/cars`, {
  //       params: {},
  //     })
  //     .then(response => {
  //       return response;
  //     });

  //     return res.data;
}

export async function getCar(id) {

    console.log(`Fetching cars from ${API_URL}`);
    let res = await axios
      .get(`${API_URL}/cars/${id}`, {
        params: {},
      })

      .then(response => {
        return response;
      });

      return res.data;
}

export async function deleteCar(car) {
  let res = await axios
    .delete(`${API_URL}/cars/${car}`, {
      params: {},
    })
    .then((response) => {
      return response;
    });

  return res.data;
}

export async function createCar(car) {
  console.log(car)
  let res = await axios
    .post(
      `${API_URL}/cars`,
      {
        id: car.id,
        name: car.name,
        maxSpeed: car.maxSpeed,
        seats: car.seats,
        doors: car.doors,
        wheels: car.wheels,
        user: car.user,
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
    .then((response) => {
      return response;
    });

  return res;
}

const api = {
  getUsers,
  getUser,
  getUserCars,
  deleteUser,
};

export default api;
