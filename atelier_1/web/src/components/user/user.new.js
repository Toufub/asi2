import React, { useState } from "react";


function UserNew() {
  const [pwd, setPwd] = useState("");
  const [surname, setSurname] = useState("");
  const [lastname, setLastname] = useState("");
  const [login, setLogin] = useState("");
  const [email, setEmail] = useState("");



  const addUser = () => {
    fetch(`https://asi2-backend-market.herokuapp.com/user`, {
      method: "POST",
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify({
          "login": login,
          "pwd": pwd,
          "lastName": lastname,
          "surName": surname,
          "email": email
      }),
    }).then(() => {
         
    }).then(() => {
          let url = "/connexion";
          window.location.replace(url);
    });
  };

  const usernameChanged = (e) => {
    setSurname(e.target?.value);
  };

  const pwdChanged = (e) => {
    setPwd(e.target?.value);
  };

  const emailChanged = (e) => {
     setEmail(e.target?.value);
   };

   const loginChanged = (e) => {
     setLogin(e.target?.value);
   };

   const lastnameChanged = (e) => {
     setLastname(e.target?.value);
   };

  return (
    <div style={{ height: "80vh" }}>
      <div className="d-flex flex-column align-items-center justify-content-center h-100 w-100">
        <div>
          <label>Surname</label>
          <input className="form-control" type="text" name="username" value={surname} onChange={usernameChanged} placeholder="Eloi" />
        </div>
        <div className="mt-2">
          <label>Lastname</label>
          <input className="form-control" type="text" name="username" value={lastname} onChange={lastnameChanged} placeholder="CAMBRAY" />
        </div>
        <div className="mt-2">
          <label>Login</label>
          <input className="form-control" type="text" name="username" value={login} onChange={loginChanged} placeholder="eloi12" />
        </div>
        <div className="mt-2">
          <label>Email</label>
          <input className="form-control" type="text" name="username" value={email} onChange={emailChanged} placeholder="eloi@test.fr" />
        </div>
        <div className="mt-2 mb-2">
          <label>Password</label>

          <input className="form-control" type="password" name="pwd" value={pwd} onChange={pwdChanged} placeholder="azerty123" />
        </div>
        <button className="btn btn-success" onClick={addUser}>
          Save
        </button>
      </div>
    </div>
  );
}

export default UserNew;
