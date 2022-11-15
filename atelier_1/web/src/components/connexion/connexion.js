import React, { useState } from "react";

function ConnexionModule() {
  const [pwd, setPwd] = useState("");
  const [username, setUsername] = useState("");


     const connect = () => {     
          fetch("https://asi2-backend-market.herokuapp.com/auth", {
          method: "POST",
          body: JSON.stringify({
               "username": username,
               "password": pwd
          })
          }).then(response => response.json())
          .then(result => {
               console.log(result.message);
          });

     };

     const usernameChanged = (e) => {
          setUsername(e.target?.value);
     };

     const pwdChanged = (e) => {
          setPwd(e.target?.value);
     };
     
  return (
    <div>
      <label>Surname</label>
      <input className="input input-form" type="text" name="username" value={username} onChange={usernameChanged} placeholder="Eloi" />
      <label>Password</label>
      <input type="password" name="pwd" value={pwd} onChange={pwdChanged} placeholder="azerty123" />
      <button className="btn btn-success" onClick={connect}>
        Connect
      </button>
    </div>
  );
}

export default ConnexionModule;
