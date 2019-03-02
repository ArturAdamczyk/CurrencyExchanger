package art.com.currencyexchanger.utilities;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import timber.log.Timber;

public class WebSocketFactory {
    private static final String CLASS_TAG = WebSocketFactory.class.getSimpleName();

    public static WebSocket connect(
            WebSocketListener listener,
            OkHttpClient okHttpClient,
            Request request) throws IllegalArgumentException {
        try {
            return okHttpClient.newWebSocket(request, listener);
        } catch (IllegalArgumentException e) {
            Timber.d(CLASS_TAG, e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
