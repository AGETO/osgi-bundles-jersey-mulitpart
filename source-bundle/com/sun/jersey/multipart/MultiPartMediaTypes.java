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

import java.util.Collections;
import javax.ws.rs.core.MediaType;

/**
 * <p>Convenience {@link MediaType} (and associated String)
 * manifest constants.</p>
 */
public final class MultiPartMediaTypes {

    /** "multipart/alternative" */
    public static final String MULTIPART_ALTERNATIVE = "multipart/alternative";

    /** "multipart/alternative" */
    public static final MediaType MULTIPART_ALTERNATIVE_TYPE =
            new MediaType("multipart", "alternative");

    /** "multipart/digest" */
    public static final String MULTIPART_DIGEST = "multipart/digest";

    /** "multipart/digest" */
    public static final MediaType MULTIPART_DIGEST_TYPE =
            new MediaType("multipart", "digest");

    /** "multipart/mixed" */
    public static final String MULTIPART_MIXED = "multipart/mixed";

    /** "multipart/mixed" */
    public static final MediaType MULTIPART_MIXED_TYPE =
            new MediaType("multipart", "mixed");

    /** "multipart/parallel" */
    public static final String MULTIPART_PARALLEL = "multipart/parallel";

    /** "multipart/parallel" */
    public static final MediaType MULTIPART_PARELLEL_TYPE =
            new MediaType("multipart", "parallel");


    /**
     * @return a "multipart/alternative" with a boundary parameter.
     */
    public static MediaType createAlternative() {
        return create(MULTIPART_ALTERNATIVE_TYPE);
    }

    /**
     * @return a "multipart/digest" with a boundary parameter.
     */
    public static MediaType createDigest() {
        return create(MULTIPART_DIGEST_TYPE);
    }

    /**
     * @return a "multipart/mixed" with a boundary parameter.
     */
    public static MediaType createMixed() {
        return create(MULTIPART_MIXED_TYPE);
    }

    /**
     * @return a "multipart/parallel" with a boundary parameter.
     */
    public static MediaType createParallel() {
        return create(MULTIPART_PARELLEL_TYPE);
    }

    /**
     * @return a "multipart/form-data" with a boundary parameter.
     */
    public static MediaType createFormData() {
        return create(MediaType.MULTIPART_FORM_DATA_TYPE);
    }

    private static MediaType create(MediaType mt) {
        return new MediaType(mt.getType(), mt.getSubtype(),
                Collections.singletonMap(Boundary.BOUNDARY_PARAMETER, Boundary.createBoundary()));
    }
}
