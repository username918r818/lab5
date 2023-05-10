package com.username918r818.lab5;

// import static org.junit.Assert.*;

import com.username918r818.lab5.models.*;
import com.username918r818.lab5.utils.*;

import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;
import java.util.HashMap;

import org.junit.Test;

public class AppTest {

    @Test
    public void creatingObjects() {
        Coordinates coordinates1 = new Coordinates(1, 2.0);
        Coordinates coordinates2 = new Coordinates(3, 4.0);
        Coordinates coordinates3 = new Coordinates(5, 6.0);
        Coordinates coordinates4 = new Coordinates(5, 6.0);

        assertTrue(!(coordinates1.toString().equals(coordinates4.toString())));
        assertTrue(coordinates4.toString().equals(coordinates3.toString()));

        Album album1 = new Album("Unforgiven", 5, 200000);
        Album album2 = new Album("Kvitravn", 11, 40000);
        Album album3 = new Album("V. Halmstad", 6, 30000);
        Album album4 = new Album("V. Halmstad", 6, 30000);

        assertTrue(!(album1.toString().equals(album2.toString())));
        assertTrue(album3.toString().equals(album4.toString()));

        MusicBand band1 = new MusicBand("Metallica", coordinates1, 6, MusicGenre.PROGRESSIVE_ROCK, album1);
        MusicBand band2 = new MusicBand("Wardruna", coordinates2, 2, MusicGenre.PROGRESSIVE_ROCK, album2);
        MusicBand band3 = new MusicBand("Shining", coordinates3, 1, MusicGenre.PROGRESSIVE_ROCK, album3);
        MusicBand band4 = new MusicBand("Shining", coordinates4, 1, MusicGenre.PROGRESSIVE_ROCK, album4);

        
        assertTrue(!(band1.toString().equals(band2.toString())));
        assertTrue(!(band3.toString().equals(band4.toString())));
        assertTrue(!(band2.toString().equals(band3.toString())));
        assertTrue(!(band1.toString().equals(band4.toString())));

        MusicBand band5 = new MusicBand(band3.getUUID(), "Shining", coordinates4, band3.getCreationDate(), 1, MusicGenre.PROGRESSIVE_ROCK, album4);

        assertTrue(band3.toString().equals(band5.toString()));
        assertTrue(!(band4.toString().equals(band5.toString())));

        band5.setUUID(band4.getUUID());
        band5.setCreationDate(band4.getCreationDate());

        assertTrue(!(band3.toString().equals(band5.toString())));
        assertTrue(band4.toString().equals(band5.toString()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionInCostruction() {
        Coordinates coordinates3 = new Coordinates(null, 6.0);
        coordinates3.setX(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionInSetter() {
        Coordinates coordinates = new Coordinates(5, 2.0);
        Album album = new Album("Kvitravn", 11, 40000);
        MusicBand band = new MusicBand("Wardruna", coordinates, 2, MusicGenre.PROGRESSIVE_ROCK, album);
        band.setName("");
    }

    @Test
    public void HashMapsAndJSON() {
        Coordinates coordinates1 = new Coordinates(1, 2.0);
        Coordinates coordinates2 = new Coordinates(3, 4.0);

        Album album1 = new Album("Unforgiven", 5, 200000);
        Album album2 = new Album("Kvitravn", 11, 40000);

        MusicBand band1 = new MusicBand("Metallica", coordinates1, 6, MusicGenre.PROGRESSIVE_ROCK, album1);
        MusicBand band2 = new MusicBand("Wardruna", coordinates2, 2, MusicGenre.PROGRESSIVE_ROCK, album2);

        var map1 = ModelHandler.modelToMap(band1);
        var map2 = ModelHandler.modelToMap(band2);
        var map3 = ModelHandler.modelToMap(band1);
        var map4 = ModelHandler.modelToMap(band2);

        assertTrue(map1.equals(map3));
        assertTrue(!(map1.equals(map4)));
        
        var band5 = ModelHandler.mapToModel(map2);

        assertTrue(!(band2.equals(band5)));
        assertTrue(band2.toString().equals(band5.toString()));
        assertTrue(!(band1.toString().equals(band5.toString())));

        var map5 = ModelHandler.modelToMap(band5);

        var json1 = JsonHandler.mapToJSON(map1);
        var json2 = JsonHandler.mapToJSON(map2);
        var json3 = JsonHandler.mapToJSON(map3);
        var json4 = JsonHandler.mapToJSON(map4);
        var json5 = JsonHandler.mapToJSON(map5);

        assertTrue(json1.equals(json3));
        assertTrue(json2.equals(json4));
        assertTrue(json4.equals(json5));
        assertTrue(!(json1.equals(json5)));

        var band6 = ModelHandler.mapToModel((HashMap<String, String>) JsonHandler.JSONToMap(json1));
        var band7 = ModelHandler.mapToModel((HashMap<String, String>) JsonHandler.JSONToMap(json2));
        var band8 = ModelHandler.mapToModel((HashMap<String, String>) JsonHandler.JSONToMap(json4));

        assertTrue(!(band6.toString().equals(band7.toString())));
        assertTrue(band7.toString().equals(band8.toString()));

    }

    @Test 
    public void CollectionHandlerTest(){


        Coordinates coordinates1 = new Coordinates(1, 2.0);
        Coordinates coordinates2 = new Coordinates(3, 4.0);
        Coordinates coordinates3 = new Coordinates(5, 6.0);
        Coordinates coordinates4 = new Coordinates(5, 6.0);

        Album album1 = new Album("Unforgiven", 5, 200000);
        Album album2 = new Album("Kvitravn", 11, 40000);
        Album album3 = new Album("V. Halmstad", 6, 30000);
        Album album4 = new Album("V. Halmstad", 6, 30000);

        MusicBand band1 = new MusicBand("Metallica", coordinates1, 6, MusicGenre.PROGRESSIVE_ROCK, album1);
        MusicBand band2 = new MusicBand("Wardruna", coordinates2, 2, MusicGenre.PROGRESSIVE_ROCK, album2);
        MusicBand band3 = new MusicBand("Shining", coordinates3, 1, MusicGenre.PROGRESSIVE_ROCK, album3);
        MusicBand band4 = new MusicBand("Shining", coordinates4, 1, MusicGenre.PROGRESSIVE_ROCK, album4);

        var ch1 = new CollectionHandler();

        ch1.add(band1);
        ch1.add(band2);
        ch1.add(band3);
        ch1.add(band4);

        band4.setName("new band");

        ch1.update(band4);
        ch1.removeByUUID(band4.getUUID());
    }


    
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionInAddCH() {
        Coordinates coordinates1 = new Coordinates(1, 2.0);
        Coordinates coordinates2 = new Coordinates(3, 4.0);

        Album album1 = new Album("Unforgiven", 5, 200000);
        Album album2 = new Album("Kvitravn", 11, 40000);

        MusicBand band1 = new MusicBand("Metallica", coordinates1, 6, MusicGenre.PROGRESSIVE_ROCK, album1);
        MusicBand band2 = new MusicBand(band1.getUUID(), "Shining", coordinates2, band1.getCreationDate(), 1, MusicGenre.PROGRESSIVE_ROCK, album2);

        var ch1 = new CollectionHandler();

        ch1.add(band1);
        ch1.add(band2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionInUpdateCH() {
        Coordinates coordinates1 = new Coordinates(1, 2.0);
        Coordinates coordinates2 = new Coordinates(3, 4.0);

        Album album1 = new Album("Unforgiven", 5, 200000);
        Album album2 = new Album("Kvitravn", 11, 40000);

        MusicBand band1 = new MusicBand("Metallica", coordinates1, 6, MusicGenre.PROGRESSIVE_ROCK, album1);
        MusicBand band2 = new MusicBand( "Shining", coordinates2, 1, MusicGenre.PROGRESSIVE_ROCK, album2);

        var ch1 = new CollectionHandler();

        ch1.add(band1);
        ch1.update(band2);
    }

    @Test 
    public void CHtoJSON(){


        Coordinates coordinates1 = new Coordinates(1, 2.0);
        Coordinates coordinates2 = new Coordinates(3, 4.0);
        Coordinates coordinates3 = new Coordinates(5, 6.0);
        Coordinates coordinates4 = new Coordinates(5, 6.0);

        Album album1 = new Album("Unforgiven", 5, 200000);
        Album album2 = new Album("Kvitravn", 11, 40000);
        Album album3 = new Album("V. Halmstad", 6, 30000);
        Album album4 = new Album("V. Halmstad", 6, 30000);

        MusicBand band1 = new MusicBand("Metallica", coordinates1, 6, MusicGenre.PROGRESSIVE_ROCK, album1);
        MusicBand band2 = new MusicBand("Wardruna", coordinates2, 2, MusicGenre.PROGRESSIVE_ROCK, album2);
        MusicBand band3 = new MusicBand("Shining", coordinates3, 1, MusicGenre.PROGRESSIVE_ROCK, album3);
        MusicBand band4 = new MusicBand("Shining", coordinates4, 1, MusicGenre.PROGRESSIVE_ROCK, album4);

        var ch1 = new CollectionHandler();

        ch1.add(band1);
        ch1.add(band2);
        ch1.add(band3);
        ch1.add(band4);
        var s1 = ch1.save();
        var m1 = JsonHandler.JSONToMap(s1);
        var ch2 = new CollectionHandler(ZonedDateTime.parse(m1.get("initDate").toString()));
        var m2 = JsonHandler.JSONToMap(m1.get("models"));

        for (var i : m2.keySet()) {
            ch2.add(ModelHandler.mapToModel(JsonHandler.JSONToMap(m2.get(i))));
        }

        assertTrue(ch1.show().equals(ch2.show()));

    }
}
