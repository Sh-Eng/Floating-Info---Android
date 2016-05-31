/*******************************************************************************
 * Copyright 2014 Alexandros Schillings
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package uk.co.alt236.floatinginfo.data.access.generalinfo.inforeader;

import java.util.HashMap;
import java.util.Map;

import uk.co.alt236.floatinginfo.data.access.generalinfo.inforeader.cpu.CpuData;
import uk.co.alt236.floatinginfo.data.access.generalinfo.inforeader.fgappinfo.ForegroundAppData;
import uk.co.alt236.floatinginfo.data.access.generalinfo.inforeader.memory.MemoryData;

public class InfoStore {
    public final Object mLock = new Object();

    private final Map<Key, Object> mStore = new HashMap<Key, Object>();

    private Object get(final Key key) {
        synchronized (mLock) {
            return mStore.get(key);
        }
    }

    public CpuData getCpuInfo() {
        return (CpuData) get(Key.CPU_INFO);
    }

    public ForegroundAppData getForegroundProcessInfo() {
        return (ForegroundAppData) get(Key.PROCESS_INFO);
    }

    public MemoryData getMemoryInfo() {
        return (MemoryData) get(Key.MEMORY_INFO);
    }

    private void put(final Key key, final Object value) {
        synchronized (mLock) {
            mStore.put(key, value);
        }
    }

    public void set(final CpuData value) {
        put(Key.CPU_INFO, value);
    }

    public void set(final ForegroundAppData value) {
        put(Key.PROCESS_INFO, value);
    }

    public void set(final MemoryData value) {
        put(Key.MEMORY_INFO, value);
    }

    private enum Key {
        PROCESS_INFO,
        CPU_INFO,
        MEMORY_INFO
    }
}
