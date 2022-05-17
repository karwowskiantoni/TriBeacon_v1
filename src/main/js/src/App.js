import {Layer, Stage} from "react-konva";
import React, {useEffect, useState} from "react";
import {Users} from "./components/Users.js"
import {Positions} from "./components/Positions";
import {NavigationBar} from "./components/NavigationBar";

function App() {
    const [users, setUsers] = useState(() => {
        const savedUsers = localStorage.getItem("users")
        const initialUsers = JSON.parse(savedUsers)
        return initialUsers || []
    });
    const [positions, setPositions] = useState([])
    const [reduceBeacons, setReduceBeacons] = useState(true);
    const [reduceUsers, setReduceUsers] = useState(3);
    const [activePosition, setActivePosition] = useState(null)
    const [showAll, setShowAll] = useState(false);
    // const URL = "https://tri-beacon.herokuapp.com"
    // const URL = "http://localhost:8080"
    const URL = ""
    const MAX_RANGE = 150;


    useEffect(() => {
        localStorage.setItem("users", JSON.stringify(users))
    }, [users])

    useEffect(() => {
        const interval = setInterval(async () => {
            let response = await fetch(URL + `/positions?reduceBeacons=${reduceBeacons}&reduceUsers=${reduceUsers}`);
            let json = await response.json();
            setPositions(json)
        }, 1000)
        return () => clearInterval(interval);
    }, [reduceBeacons, reduceUsers])

    useEffect(() => {
        async function updateConnection(x, y, id) {
            let usersInRange = []
            for (const user of users) {
                if (id !== user.id && Math.sqrt((x - user.x) ** 2 + (y - user.y) ** 2) < MAX_RANGE) {
                    usersInRange.push(user)
                }
            }
            await fetch(URL + "/update", {
                method: "POST",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({connections: usersInRange.map(user => user.id), name: id})
            });
            return usersInRange;
        }

        async function fetchAPI() {
            for (const user of users) {
                await updateConnection(user.x, user.y, user.id);
            }

            let response = await fetch(URL + `/positions?reduceBeacons=${reduceBeacons}&reduceUsers=${reduceUsers}`);
            let json = await response.json();
            setPositions(json)
        }

        fetchAPI();
    }, [reduceBeacons, reduceUsers, users]);

    return (
        <div>
            <NavigationBar URL={URL}
                           reduceBeacons={reduceBeacons}
                           reduceUsers={reduceUsers}
                           setReduceBeacons={setReduceBeacons}
                           setReduceUsers={setReduceUsers}
                           setUsers={setUsers}
                           setShowAll={setShowAll}
                           showAll={showAll}
                           users={users}/>
            <Stage width={window.innerWidth - 100} height={window.innerHeight - 100}>
                <Layer>
                    <Positions positions={positions} setPositions={setPositions} showAll={showAll}
                               activePosition={activePosition}/>
                    <Users users={users} setUsers={setUsers} URL={URL} setActivePosition={setActivePosition}/>
                </Layer>
            </Stage>
        </div>
    );
}

export default App;
