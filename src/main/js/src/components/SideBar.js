import {Button, Card, Offcanvas} from "react-bootstrap";
import {useEffect, useState} from "react";

export function SideBar({URL}) {
    const [show, setShow] = useState(false);
    const [devices, setDevices] = useState([{name: "placeholder"}]);
    useEffect(async () => {
        let response = await fetch(URL + `/users`);
        let json = await response.json();
        setDevices(json)
    }, [show])
    return (
        <div>
            <Button variant="primary" onClick={() => setShow(true)} style={{marginLeft: 20, marginRight: 20}}>
                Devices
            </Button>
            <Offcanvas placement={"end"} show={show} onHide={() => setShow(false)}>
                <Offcanvas.Header closeButton>
                    <Offcanvas.Title>available devices</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    {devices.map((device) =>
                        <Card style={{margin: 10}}>
                            <Card.Body>{device.name}</Card.Body>
                        </Card>
                    )}
                </Offcanvas.Body>
            </Offcanvas>
        </div>
    );
}
