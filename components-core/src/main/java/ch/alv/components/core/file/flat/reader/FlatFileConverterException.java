/*
 *  Copyright 2005 AverConsulting Inc.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ch.alv.components.core.file.flat.reader;

/**
 * Indicates that an unrecoverable exception occured while transforming a String or File
 * to java objects.
 * 
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class FlatFileConverterException extends RuntimeException {

	public FlatFileConverterException(String msg) {
		super(msg);
	}

	public FlatFileConverterException(Throwable throwable) {
		super(throwable);
	}

    public FlatFileConverterException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
