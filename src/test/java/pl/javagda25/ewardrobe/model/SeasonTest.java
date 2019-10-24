package pl.javagda25.ewardrobe.model;

import org.junit.Assert;
import org.junit.Test;

public class SeasonTest {

    @Test
    public void shouldReturnTrue() {
        Season season = new Season(SeasonName.AUTUMN);
        Assert.assertTrue(season.inSeason());

    }

    @Test
    public void returnFalse() {
        Season season = new Season(SeasonName.SUMMER);
        Assert.assertFalse(season.inSeason());
    }
}