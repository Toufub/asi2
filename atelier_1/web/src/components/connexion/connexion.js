import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { selectedClientId } from "../../core/client.selector";
import { setSelectedClientId } from "../../core/client.actions.js";
import { Card } from "react-bootstrap";
import { Link } from "react-router-dom";

function ConnexionModule() {
  const [pwd, setPwd] = useState("");
  const [username, setUsername] = useState("");
  const userId = useSelector(selectedClientId);

  const dispatch = useDispatch();

  const selectClient = (id) => {
    dispatch(setSelectedClientId(id));
  };

  const connect = () => {
    fetch("https://asi2-backend-market.herokuapp.com/auth", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: username,
        password: pwd,
      }),
    })
      .then((response) => response.json())
      .then((result) => {
        if (result?.status === 403) alert("Wrong login/password");
        else selectClient(result);
      });
  };

  const usernameChanged = (e) => {
    setUsername(e.target?.value);
  };

  const pwdChanged = (e) => {
    setPwd(e.target?.value);
  };

  return (
    <div style={{ height: "80vh" }}>
      <div className="d-flex flex-column align-items-center justify-content-center h-100 w-100">
        {userId ? (
          <div>
            <div style={{ fontSize: "300%" }}>
              <b>You are already connected</b>
            </div>
            <div className="d-flex align-items-center justify-content-center">
              <Card style={{ backgroundColor: "rgb(219 218 218)" }}>
                <Card.Text className="p-3">
                  <Link to="/myCards" style={{fontSize:"300%"}}>My cards</Link>
                </Card.Text>
              </Card>
            </div>
          </div>
        ) : (
          <div>
            <div>
              <label>Surname</label>
              <input className="form-control" type="text" name="username" value={username} onChange={usernameChanged} placeholder="Eloi" />
            </div>
            <div className="mt-2 mb-2">
              <label>Password</label>

              <input className="form-control" type="password" name="pwd" value={pwd} onChange={pwdChanged} placeholder="azerty123" />
            </div>
            <div className="mb-2 text-center">
              <a href="/newUser">New user</a>
            </div>
            <button className="btn btn-success w-100" onClick={connect}>
              Connect
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default ConnexionModule;
