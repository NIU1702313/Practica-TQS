package cat.uab;

public class RandomNumberGenerator {
    public int randomInt(int min, int max) {
        return (int) (Math.round(Math.random() * (max - min) + min));
    }
}
