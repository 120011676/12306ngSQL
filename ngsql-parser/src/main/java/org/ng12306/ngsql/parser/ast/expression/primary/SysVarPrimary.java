/*
 * Copyright 2012-2013 NgSql Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ng12306.ngsql.parser.ast.expression.primary;

import org.ng12306.ngsql.parser.ast.fragment.VariableScope;
import org.ng12306.ngsql.parser.visitor.SQLASTVisitor;


/**
 * 
* [添加说明]
* @author: <a href="mailto:lvbomr@gmail.com">lvbo</a>
* @date: 2013-5-26 上午11:15:24
* @version: 1.0
 */
public class SysVarPrimary extends VariableExpression {
    private final VariableScope scope;
    /** excluding starting "@@", '`' might be included */
    private final String varText;
    private final String varTextUp;

    public SysVarPrimary(VariableScope scope, String varText, String varTextUp) {
        this.scope = scope;
        this.varText = varText;
        this.varTextUp = varTextUp;
    }

    /**
     * @return never null
     */
    public VariableScope getScope() {
        return scope;
    }

    public String getVarTextUp() {
        return varTextUp;
    }

    public String getVarText() {
        return varText;
    }

    @Override
    public void accept(SQLASTVisitor visitor) {
        visitor.visit(this);
    }
}
