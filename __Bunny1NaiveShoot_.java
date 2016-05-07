class __Bunny1NaiveShoot_ extends JJRobot {
    void main() {
        while(true) {
            for(int angle=0; angle<360; angle+=10) {
                int dist = scan(angle, 10);
                if (dist > 0) {
                    cannon(angle, dist);
                }
            }
        }
    }
}