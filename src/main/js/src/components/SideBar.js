import {Button, Offcanvas} from "react-bootstrap";
import {useState} from "react";

export function SideBar() {
    const [show, setShow] = useState(false);
    return (
        <>
            <Button variant="primary" onClick={() => setShow(true)} style={{marginLeft: 20, marginRight: 20}}>
                Devices
            </Button>

            <Offcanvas placement={"end"} show={show} onHide={() => setShow(false)}>
                <Offcanvas.Header closeButton>
                    <Offcanvas.Title>available devices</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    Some text as placeholder. In real life you can have the elements you
                    have chosen. Like, text, images, lists, etc.
                </Offcanvas.Body>
            </Offcanvas>
        </>
    );
}
