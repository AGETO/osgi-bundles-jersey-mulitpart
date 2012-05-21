/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010-2011 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.jersey.multipart;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Injectable JavaBean containing the configuration parameters for
 * <code>jersey-multipart</code> as used in this particular application.</p>
 */
public class MultiPartConfig {


    // ------------------------------------------------------------- Constructor


    /**
     * <p>Load and customize (if necessary) the configuration values for the
     * <code>jersey-multipart</code> module.</p>
     *
     * @exception IllegalArgumentException if the configuration resource
     *  exists, but there are problems reading it
     */
    public MultiPartConfig() {
        configure();
    }


    // ------------------------------------------------------ Manifest Constants


    /**
     * <p>Name of a properties resource that (if found in the classpath
     * for this application) will be used to configure the settings returned
     * by our getter methods.</p>
     */
    public static final String MULTI_PART_CONFIG_RESOURCE =
            "jersey-multipart-config.properties";


    /**
     * <p>Name of the resource property for the <code>bufferThreshold</code>
     * property.</p>
     */
    public static final String BUFFER_THRESHOLD_NAME = "bufferThreshold";


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>The threshold size (in bytes) above which a body part entity will be
     * buffered to disk instead of being held in memory.</p>
     */
    private int bufferThreshold = 4096;


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Return the size (in bytes) of the entity of an incoming
     * {@link BodyPart} before it will be buffered to disk.  If not
     * customized, the default value is 4096.</p>
     */
    public int getBufferThreshold() {
        return bufferThreshold;
    }


    // --------------------------------------------------------- Private Methods


    /**
     * <p>Configure the values returned by this instance's getters based on
     * the contents of a properties resource, if it exists on the classpath
     * for this application.</p>
     *
     * @exception IllegalArgumentException if the configuration resource
     *  exists, but there are problems reading it
     */
    private void configure() {

        // Identify the class loader we will use
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = this.getClass().getClassLoader();
        }

        // Attempt to find our properties resource
        InputStream stream = null;
        try {
            stream = loader.getResourceAsStream(MULTI_PART_CONFIG_RESOURCE);
            if (stream == null) {
                return;
            }
            Properties props = new Properties();
            props.load(stream);
            String value = null;
            value = props.getProperty(BUFFER_THRESHOLD_NAME);
            if (value != null) {
                System.out.println("Setting bufferThreshold to " + value);
                this.bufferThreshold = Integer.valueOf(value);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // Pass through
                }
            }
        }

    }


}
