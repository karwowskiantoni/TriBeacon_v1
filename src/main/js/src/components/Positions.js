import {Group, Line, Circle} from "react-konva";
import React from "react";

export function Positions({positions, setPositions, showAll, activePosition}) {
    return (
        positions.map((position) =>
            showAll || activePosition === position.userDto.name ? 
            <Group>
                <Circle
                    key={position.userDto.name}
                    id={position.userDto.name}
                    x={position.center.coordinates[0]}
                    y={position.center.coordinates[1]}
                    radius={10}
                    fill={"#0990c7"}                   
                />
                <Line
                    key={position.polygon.coordinates}
                    points={position.polygon.coordinates.flat(5)}
                    fill={'#759daa'}
                    stroke={'blue'}
                    strokeWidth={2}
                    opacity={0.2}
                    closed={true}
                />
            </Group> : null
        )
    )
}
