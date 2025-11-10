package com.codingkiddo.api.streams.internal;

import org.jetbrains.annotations.NotNull;

public final class Parameters {

    public Runnable closeHandler;

    public static Parameters wrapWithCloseHandler(Parameters params, @NotNull Runnable closeHandler) {
        final Parameters newParams;
        if (params == null) {
            newParams = new Parameters();
            newParams.closeHandler = closeHandler;
        } else {
            newParams = params;
        }
        return newParams;
    }
}
