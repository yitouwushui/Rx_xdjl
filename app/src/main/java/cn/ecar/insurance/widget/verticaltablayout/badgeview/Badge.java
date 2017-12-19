package cn.ecar.insurance.widget.verticaltablayout.badgeview;

import android.graphics.PointF;
import android.view.View;

/**
 * @author chqiu
 *         Email:qstumn@163.com
 */

public interface Badge {

    Badge setBadgeNumber(int badgeNum);

    int getBadgeNumber();

    Badge setExactMode(boolean isExact);

    boolean isExactMode();

    Badge setShowShadow(boolean showShadow);

    boolean isShowShadow();

    Badge setBadgeBackgroundColor(int color);

    int getBadgeBackgroundColor();

    Badge setBadgeNumberColor(int color);

    int getBadgeNumberColor();

    Badge setBadgeNumberSize(float size, boolean isSpValue);

    float getBadgeNumberSize(boolean isSpValue);

    Badge setBadgePadding(float padding, boolean isDpValue);

    float getBadgePadding(boolean isDpValue);

    boolean isDraggable();

    Badge setBadgeGravity(int gravity);

    int getBadgeGravity();

    Badge setGravityWidthOffset(int WidthOffset, boolean isDpValue);

    int getGravityWidthOffset(boolean isDpValue);

    Badge setGravityHeightOffset(int HeightOffset, boolean isDpValue);

    int getGravityHeightOffset(boolean isDpValue);

    Badge setOnDragStateChangedListener(OnDragStateChangedListener l);

    PointF getDragCenter();

    Badge bindTarget(View view);

    void hide(boolean animate);

    interface OnDragStateChangedListener {
        int STATE_START = 1;
        int STATE_DRAGGING = 2;
        int STATE_DRAGGING_OUT_OF_RANGE = 3;
        int STATE_CANCELED = 4;
        int STATE_SUCCEED = 5;

        void onDragStateChanged(int dragState, Badge badge, View targetView);
    }
}
