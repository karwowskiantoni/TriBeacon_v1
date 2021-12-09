package com.example.TriBeacon.Model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class AlgorithmTest {
    private final Algorithm algorithm = new Algorithm();

//    @Test
//    void BDSBeaconPaths1() {
//        User core = new User("mistrzu");
//        User first = new User("1");
//        User second = new User("2", true);
//        User third = new User("3");
//        User fourth = new User("4");
//        User fifth = new User("5", true);
//        User sixth = new User("6");
//        User seventh = new User("7", true);
//        core.updateConnections(new HashSet<>(Arrays.asList(first, second, third)));
//        first.updateConnections(new HashSet<>(Collections.singletonList(fourth)));
//        third.updateConnections(new HashSet<>(Collections.singletonList(fifth)));
//        fourth.updateConnections(new HashSet<>(Collections.singletonList(sixth)));
//        sixth.updateConnections(new HashSet<>(Collections.singletonList(seventh)));
//        Map<String, Integer> test = algorithm.BDSBeaconPaths(core);
//        assertIterableEquals(new HashSet<>(Arrays.asList("2", "5", "7")), test.keySet());
//        assertIterableEquals(new HashSet<>(Arrays.asList(1, 2, 4)), test.values());
//    }
//
//    @Test
//    void BDSBeaconPaths2() {
//        User core = new User("mistrzu");
//        User first = new User("1");
//        User second = new User("2", true);
//        User third = new User("3");
//        User fourth = new User("4");
//        User fifth = new User("5", true);
//        User sixth = new User("6");
//        User seventh = new User("7", true);
//        core.updateConnections(new HashSet<>(Arrays.asList(first, third, second)));
//        first.updateConnections(new HashSet<>(Collections.singletonList(fourth)));
//        third.updateConnections(new HashSet<>(Collections.singletonList(fifth)));
//        fourth.updateConnections(new HashSet<>(Collections.singletonList(sixth)));
//        sixth.updateConnections(new HashSet<>(Collections.singletonList(seventh)));
//        Map<String, Integer> test = algorithm.BDSBeaconPaths(core);
//        assertIterableEquals(new HashSet<>(Arrays.asList("2", "5", "7")), test.keySet());
//        assertIterableEquals(new HashSet<>(Arrays.asList(1, 2, 4)), test.values());
//    }
//
//    @Test
//    void BDSBeaconPaths3() {
//        User core = new User("mistrzu");
//        User first = new User("1");
//        User second = new User("2", true);
//        User third = new User("3");
//        User fourth = new User("4");
//        User fifth = new User("5", true);
//        User sixth = new User("6");
//        User seventh = new User("7", true);
//        core.updateConnections(new HashSet<>(Arrays.asList(second, first, third)));
//        first.updateConnections(new HashSet<>(Collections.singletonList(fourth)));
//        third.updateConnections(new HashSet<>(Collections.singletonList(fifth)));
//        fourth.updateConnections(new HashSet<>(Collections.singletonList(sixth)));
//        sixth.updateConnections(new HashSet<>(Collections.singletonList(seventh)));
//        Map<String, Integer> test = algorithm.BDSBeaconPaths(core);
//        assertIterableEquals(new HashSet<>(Arrays.asList("2", "5", "7")), test.keySet());
//        assertIterableEquals(new HashSet<>(Arrays.asList(1, 2, 4)), test.values());
//    }
//
//    @Test
//    void BDSBeaconPaths4() {
//        User core = new User("mistrzu");
//        User first = new User("1");
//        User second = new User("2", true);
//        User third = new User("3");
//        User fourth = new User("4");
//        User fifth = new User("5", true);
//        User sixth = new User("6");
//        User seventh = new User("7", true);
//        core.updateConnections(new HashSet<>(Arrays.asList(second, third, first)));
//        first.updateConnections(new HashSet<>(Collections.singletonList(fourth)));
//        third.updateConnections(new HashSet<>(Collections.singletonList(fifth)));
//        fourth.updateConnections(new HashSet<>(Collections.singletonList(sixth)));
//        sixth.updateConnections(new HashSet<>(Collections.singletonList(seventh)));
//        Map<String, Integer> test = algorithm.BDSBeaconPaths(core);
//        assertIterableEquals(new HashSet<>(Arrays.asList("2", "5", "7")), test.keySet());
//        assertIterableEquals(new HashSet<>(Arrays.asList(1, 2, 4)), test.values());
//    }
//
//    @Test
//    void BDSBeaconPaths5() {
//        User core = new User("mistrzu");
//        User first = new User("1");
//        User second = new User("2", true);
//        User third = new User("3");
//        User fourth = new User("4");
//        User fifth = new User("5", true);
//        User sixth = new User("6");
//        User seventh = new User("7", true);
//        core.updateConnections(new HashSet<>(Arrays.asList(third, second, first)));
//        first.updateConnections(new HashSet<>(Collections.singletonList(fourth)));
//        third.updateConnections(new HashSet<>(Collections.singletonList(fifth)));
//        fourth.updateConnections(new HashSet<>(Collections.singletonList(sixth)));
//        sixth.updateConnections(new HashSet<>(Collections.singletonList(seventh)));
//        Map<String, Integer> test = algorithm.BDSBeaconPaths(core);
//        assertIterableEquals(new HashSet<>(Arrays.asList("2", "5", "7")), test.keySet());
//        assertIterableEquals(new HashSet<>(Arrays.asList(1, 2, 4)), test.values());
//    }
//
//    @Test
//    void BDSBeaconPaths6() {
//        User core = new User("mistrzu");
//        User first = new User("1");
//        User second = new User("2", true);
//        User third = new User("3");
//        User fourth = new User("4");
//        User fifth = new User("5", true);
//        User sixth = new User("6");
//        User seventh = new User("7", true);
//        core.updateConnections(new HashSet<>(Arrays.asList(third, first, second)));
//        first.updateConnections(new HashSet<>(Collections.singletonList(fourth)));
//        third.updateConnections(new HashSet<>(Collections.singletonList(fifth)));
//        fourth.updateConnections(new HashSet<>(Collections.singletonList(sixth)));
//        sixth.updateConnections(new HashSet<>(Collections.singletonList(seventh)));
//        Map<String, Integer> test = algorithm.BDSBeaconPaths(core);
//        assertIterableEquals(new HashSet<>(Arrays.asList("2", "5", "7")), test.keySet());
//        assertIterableEquals(new HashSet<>(Arrays.asList(1, 2, 4)), test.values());
//    }
}