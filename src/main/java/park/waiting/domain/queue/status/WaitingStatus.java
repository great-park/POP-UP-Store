package park.waiting.domain.queue.status;

import park.waiting.domain.queue.entity.Waiting;

public enum WaitingStatus {
    WAITING,
    CANCELED,
    DONE;

    public static boolean isWaiting(Waiting waiting) {
        return waiting.getWaitingStatus().equals(WAITING);
    }
}
