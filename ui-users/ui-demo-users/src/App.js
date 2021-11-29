
import { useEffect, useState } from 'react';
import './App.css';

function App() {
  const [users, setUsers] = useState([]);
  const [message, setMessage] = useState("Hello FROM REACT!!");
  useEffect(() => {
    setUsers([{ name: "ABC", age: 1 }, { name: "XYZ",age: 2}])
  }, []);

  const getGreet = () => {
    fetch('http://172.31.0.20:8080/').then((res)=> res.json() ).then((result)=> {
      console.log(result)
      setMessage(result.data)
    })
  }
  return (
    <div className="App">
      <h1> Docker - React app from conatiner</h1>
      <hr/>
      <button onClick={getGreet}>Get Message</button>
      {message}
      <hr/>
      <button>Add New User</button>
      <table>
        <thead>
          <th>
            <td>Name</td>
            <td>Age</td>
          </th>
        </thead>
        <tbody>
          {
            users.map((user, i)=> {
              return <tr>
                <td>{user.name}</td>
                <td>{user.age}</td>
                <td><button>Delete</button></td>
              </tr>
            })
          }
        </tbody>
      </table>
    </div>
  );
}

export default App;
