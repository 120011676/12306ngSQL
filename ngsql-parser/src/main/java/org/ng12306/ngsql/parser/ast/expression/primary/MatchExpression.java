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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ng12306.ngsql.parser.ast.expression.Expression;
import org.ng12306.ngsql.parser.visitor.SQLASTVisitor;


/**
 * 
* [添加说明]
* @author: <a href="mailto:lvbomr@gmail.com">lvbo</a>
* @date: 2013-5-26 上午11:14:08
* @version: 1.0
 */
public class MatchExpression extends PrimaryExpression {
    public static enum Modifier {
        /** no modifier */
        _DEFAULT,
        IN_BOOLEAN_MODE,
        IN_NATURAL_LANGUAGE_MODE,
        IN_NATURAL_LANGUAGE_MODE_WITH_QUERY_EXPANSION,
        WITH_QUERY_EXPANSION
    }

    private final List<Expression> columns;
    private final Expression pattern;
    private final Modifier modifier;

    /**
     * @param modifier never null
     */
    public MatchExpression(List<Expression> columns, Expression pattern, Modifier modifier) {
        if (columns == null || columns.isEmpty()) {
            this.columns = Collections.emptyList();
        } else if (columns instanceof ArrayList) {
            this.columns = columns;
        } else {
            this.columns = new ArrayList<Expression>(columns);
        }
        this.pattern = pattern;
        this.modifier = modifier;
    }

    public List<Expression> getColumns() {
        return columns;
    }

    public Expression getPattern() {
        return pattern;
    }

    public Modifier getModifier() {
        return modifier;
    }

    @Override
    public void accept(SQLASTVisitor visitor) {
        visitor.visit(this);
    }
}