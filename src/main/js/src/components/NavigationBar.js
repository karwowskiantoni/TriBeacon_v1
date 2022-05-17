import {Navbar, Button, Form, FormControl} from "react-bootstrap";
import {SideBar} from "./SideBar";

export function NavigationBar({
                                  URL,
                                  reduceBeacons,
                                  setReduceBeacons,
                                  reduceUsers,
                                  setReduceUsers,
                                  users,
                                  setUsers,
                                  showAll,
                                  setShowAll
                              }) {
    return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
            <SideBar URL={URL}/>
            <Button style={{minWidth: 200, marginRight: 20}}
                    variant={reduceBeacons ? "success" : "danger"}
                    onClick={() => setReduceBeacons(!reduceBeacons)}
            >
                reduce by beacon range
            </Button>
            <Form className="d-flex">
                <Button style={{minWidth: 200}}
                        disabled
                        variant={reduceUsers > 0 ? "success" : "danger"}
                >
                    reduce by user range:
                </Button>
                <FormControl
                    type="number"

                    style={{maxWidth: 55, marginRight: 20}}
                    onChange={(e) => setReduceUsers(e.target.value)}
                    value={reduceUsers}
                />
            </Form>
            <Button
                style={{minWidth: 200, marginRight: 20}}
                variant={"warning"}
                onClick={() => setUsers([...users, {
                    id: "u" + users.length,
                    x: 100,
                    y: 100,
                    isDragging: false,
                    isBeacon: false
                }])}>
                add user
            </Button>
            <Button
                style={{minWidth: 200, marginRight: 20}}
                variant={"light"}
                onClick={() => setUsers([...users, {
                    id: "b" + users.length,
                    x: 200,
                    y: 100,
                    isDragging: false,
                    isBeacon: true
                }])}>
                add beacon
            </Button>
            <Button
                style={{minWidth: 200, marginRight: 20}}
                variant={showAll ? "success" : "outline-success"}
                onClick={() => setShowAll(!showAll)}> {showAll ? "hide areas" : "show areas"}</Button>
            <Button
                style={{minWidth: 200, marginLeft: 400}}
                id='deletingButton'
                variant={"danger"}
                onClick={async () => {
                    const response = await fetch(URL + `/delete`)
                    if (response.status === 200) {
                        setUsers([])
                    }
                }}>Delete all users
            </Button>
        </Navbar>
    );
}