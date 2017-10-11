package com.oldwoodsoftware.steward.platform.event;

import com.oldwoodsoftware.steward.platform.type.Configuration;

/**
 * Created by Nails on 28.09.2017.
 */

public interface PlateEventListener {
    void onPlateConfigurationChanged(Configuration configuration);
}
