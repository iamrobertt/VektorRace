/*
 * Copyright (c) 2024.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package it.unicam.cs.NeculaRobertGabriel123390.api.model.handler;

import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.collision.CollisionHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.crash.CrashHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.handler.win.WinHandler;
import it.unicam.cs.NeculaRobertGabriel123390.api.model.log.LoadingLogger;

import java.util.List;


/**
 * Provides utility methods for validating {@link RaceHandler} instances and their order.
 *
 * <p>The {@code HandlerValidator} class includes methods to validate individual {@code RaceHandler} instances
 * and to ensure that a list of handlers follows a specific order required for proper functioning of the race handling.</p>
 */
public final class HandlerValidator {


    /**
     * Don't allow user to instantiate a HandlerValidator object because that's not how this class is meant to work.
     */
    private HandlerValidator() {}


    /**
     * Validates a {@code RaceHandler} instance.
     *
     * <p>This method checks whether the provided {@code RaceHandler} is {@code null}. If it is, a
     * {@code NullPointerException} is thrown.</p>
     *
     * @param handler The {@code RaceHandler} instance to validate.
     * @throws NullPointerException if {@code handler} is {@code null}.
     */
    public static void validate(RaceHandler handler) {
        if(handler == null)
            throw new NullPointerException("handler is null");
    }


    /**
     * Validates the order of {@code RaceHandler} instances in a list.
     *
     * <p>This method ensures that the provided list of {@code RaceHandler} instances is not {@code null}
     * or empty, and that the handlers are in a specific required order. The expected order is:
     * 1. A {@code WinHandler}
     * 2. A {@code CrashHandler}
     * 3. A {@code CollisionHandler}</p>
     *
     * <p>If the list does not meet these criteria, an {@code IllegalArgumentException} is logged and thrown.</p>
     *
     * @param handlers The list of {@code RaceHandler} instances to validate.
     * @throws NullPointerException if {@code handlers} is {@code null}.
     * @throws IllegalArgumentException if {@code handlers} is empty or does not follow the required order.
     */
    public static void validateHandlersOrder(List<RaceHandler> handlers){
        if(handlers == null)
            throw new NullPointerException("handlers is null");
        if(handlers.isEmpty())
            throw new IllegalArgumentException("handlers is empty");

        if (!(handlers.getFirst() instanceof WinHandler))
            LoadingLogger.logErrorAndThrow("First handler is not a WinHandler.", IllegalArgumentException.class);

        if (!(handlers.get(1) instanceof CrashHandler))
            LoadingLogger.logErrorAndThrow("Second handler is not a CrashHandler.", IllegalArgumentException.class);

        if (!(handlers.get(2) instanceof CollisionHandler))
            LoadingLogger.logErrorAndThrow("Third handler is not a CollisionHandler.", IllegalArgumentException.class);
    }
}
