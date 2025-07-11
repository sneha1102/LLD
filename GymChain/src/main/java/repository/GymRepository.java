package main.java.repository;

import main.java.models.Gym;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GymRepository {
    private final ConcurrentMap<String, Gym> gyms = new ConcurrentHashMap<>();

    public Gym getGymById(String gymId) {
        return gyms.get(gymId);
    }

    public void saveGym(Gym gym) {
        gyms.put(gym.getId(), gym);
    }

    public void deleteGym(String gymId) {
        gyms.remove(gymId);
    }

    public Collection<Gym> getAllGyms() {
        return gyms.values();
    }
}
