public class City {
    double x;
    double y;

    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getDistance(City destiny){
        //return Math.pow(destiny.getX() - this.getX(), 2) + Math.pow(destiny.getY() - this.getY(), 2);
        double xDistance = Math.abs(getX() - destiny.getX());
        double yDistance = Math.abs(getY() - destiny.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
        return distance;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}
