/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.standard.processor;

import org.thymeleaf.context.ITemplateProcessingContext;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.ITemplateEnd;
import org.thymeleaf.model.ITemplateStart;
import org.thymeleaf.processor.template.AbstractTemplateProcessor;
import org.thymeleaf.processor.template.ITemplateStructureHandler;
import org.thymeleaf.standard.inline.StandardCSSInliner;
import org.thymeleaf.standard.inline.StandardHTMLInliner;
import org.thymeleaf.standard.inline.StandardJavaScriptInliner;
import org.thymeleaf.standard.inline.StandardTextInliner;
import org.thymeleaf.standard.inline.StandardXMLInliner;
import org.thymeleaf.templatemode.TemplateMode;

/**
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 *
 */
public final class StandardInlineEnablementTemplateProcessor extends AbstractTemplateProcessor {

    public static final int PRECEDENCE = 10;

    public StandardInlineEnablementTemplateProcessor(final TemplateMode templateMode) {
        super(templateMode, PRECEDENCE);
    }


    @Override
    public void doProcessTemplateStart(
            final ITemplateProcessingContext processingContext, final ITemplateStart templateStart,
            final ITemplateStructureHandler structureHandler) {

        switch (getTemplateMode()) {

            case HTML:
                structureHandler.setInliner(StandardHTMLInliner.INSTANCE);
                break;
            case XML:
                structureHandler.setInliner(StandardXMLInliner.INSTANCE);
                break;
            case TEXT:
                structureHandler.setInliner(StandardTextInliner.INSTANCE);
                break;
            case JAVASCRIPT:
                structureHandler.setInliner(StandardJavaScriptInliner.INSTANCE);
                break;
            case CSS:
                structureHandler.setInliner(StandardCSSInliner.INSTANCE);
                break;
            default:
                throw new TemplateProcessingException(
                        "Unrecognized template mode: " + getTemplateMode() + ", cannot initialize inlining!");

        }

    }


    @Override
    public void doProcessTemplateEnd(final ITemplateProcessingContext processingContext, final ITemplateEnd templateEnd,
                                     final ITemplateStructureHandler structureHandler) {

        // Empty - nothing to be done on template end

    }

}
