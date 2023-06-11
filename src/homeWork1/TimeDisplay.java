package homeWork1;

public class TimeDisplay {
    public static void main(String[] args) {
        // Потік для відображення часу кожну секунду
        Thread timeThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime) / 1000; // Пауза на 1 секунда
                System.out.println("Від запуску минуло : " + elapsedTime + " секунд");
                try {
                    Thread.sleep(1000); // Пауза на 1 секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        timeThread.start();

        // Потік для виведення повідомлення кожні 5 секунд
        Thread messageThread = new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(5000); // Пауза на 5 секунд
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Минуло 5 секунд");
            }
        });
        messageThread.start();
    }

}
