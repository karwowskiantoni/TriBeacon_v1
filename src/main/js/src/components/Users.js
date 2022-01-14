import {Circle, Group, Text} from "react-konva";
import React, {useRef} from "react";

export function Users({users, setUsers, URL, setActivePosition}) {
    const currentUsers = useRef(users)
    async function handleDragEnd(x, y, id, isBeacon) {
        if (isBeacon) {
            await subscribeBeacon(x, y, id);
        } else {
            await subscribeUser(id);
        }
        await updateUsersList(x, y, id, isBeacon);
    }

    return (
        users.map((user) =>
            <Group key={user.id}>
                <Circle
                    key={user.id}
                    id={user.id}
                    x={user.x}
                    y={user.y}
                    opacity={0.8}
                    radius={user.isBeacon? 12 : 8}
                    fill={user.isBeacon ? "#39aa6b" : "#ff4d4d"}
                    onDragEnd={(e) => handleDragEnd(e.target.x(), e.target.y(), user.id, user.isBeacon)}
                    onMouseOver={(e) => setActivePosition(user.id)}
                    onMouseLeave={(e) => setActivePosition(null)}
                    draggable
                />
                <Text text={user.id} x={user.x - 7} y={user.y + 22}/>
            </Group>
        )
    )

    async function updateUsersList(x, y, id, isBeacon) {
        let newUsers = [...users]
        let index = users.findIndex(user => user.id === id);
        newUsers[index] = {id: id, x: x, y: y, isDragging:false, isBeacon: isBeacon}
        setUsers(newUsers);
        currentUsers.current = newUsers
    }

    async function subscribeUser(id) {
        await fetch(URL + `/subscribe/user?name=${id}`, {method: "POST"});
    }

    async function subscribeBeacon(x, y, id) {
        await fetch(URL + `/subscribe/beacon?name=${id}&x=${x}&y=${y}`, {method: "POST"});
    }
}