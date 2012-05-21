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

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import org.jvnet.mimepull.MIMEPart;

/**
 * Proxy class representing the entity of a {@link BodyPart} when a
 * {@link MultiPart} entity is received and parsed.
 * <p>
 * Its primary purpose is to provide an input stream to retrieve the actual data.
 * However, it also transparently deals with storing the data in a temporary disk
 * file, if it is larger than a configurable size; otherwise, the data is stored
 * in memory for faster processing.
 */
public class BodyPartEntity implements Closeable {
    private final MIMEPart mimePart;

    /**
     * Construct a new {@link BodyPartEntity} with a {@link MIMEPart}.
     *
     * @param mimePart <code>MIMEPart</code> containing the input stream
     *        of this body part entity.
     */
    public BodyPartEntity(MIMEPart mimePart) {
        this.mimePart = mimePart;
    }


    /**
     * Get the input stream to the raw bytes of this body part entity.
     * 
     * @return the input stream of the body part entity.
     */
    public InputStream getInputStream() {
        return mimePart.read();
    }

    /**
     * Clean up temporary file(s), if any were utilized.
     */
    public void cleanup() {
        mimePart.close();
    }
    
    // Closeable

    /**
     * Defer to {@link #cleanup}.
     * 
     */
    public void close() throws IOException {
        cleanup();
    }
}