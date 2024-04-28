public class Main {
    public static void main(String[] args) {
        String[] ClassArgs = {"arg1", "arg2", "arg3"};

        new Thread(() -> {
            try {
                GUIDash.main(ClassArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                ChatServerServiceServer.main(ClassArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                AttendanceServerServiceServer.main(ClassArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
