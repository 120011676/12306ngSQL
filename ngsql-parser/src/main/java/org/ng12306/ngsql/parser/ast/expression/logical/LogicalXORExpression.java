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
package org.ng12306.ngsql.parser.ast.expression.logical;

import java.util.Map;

import org.ng12306.ngsql.parser.ast.expression.BinaryOperatorExpression;
import org.ng12306.ngsql.parser.ast.expression.Expression;
import org.ng12306.ngsql.parser.ast.expression.primary.literal.LiteralBoolean;
import org.ng12306.ngsql.parser.util.ExprEvalUtils;
import org.ng12306.ngsql.parser.visitor.SQLASTVisitor;


/**
 * 
* [添加说明]
* @author: <a href="mailto:lvbomr@gmail.com">lvbo</a>
* @date: 2013-5-25 下午12:36:02
* @version: 1.0
 */
public class LogicalXORExpression extends BinaryOperatorExpression {
    public LogicalXORExpression(Expression left, Expression right) {
        super(left, right, PRECEDENCE_LOGICAL_XOR);
    }

    @Override
    public String getOperator() {
        return "XOR";
    }

    @Override
    public Object evaluationInternal(Map<? extends Object, ? extends Object> parameters) {
        Object left = leftOprand.evaluation(parameters);
        Object right = rightOprand.evaluation(parameters);
        if (left == null || right == null) return null;
        if (left == UNEVALUATABLE || right == UNEVALUATABLE) return UNEVALUATABLE;
        boolean b1 = ExprEvalUtils.obj2bool(left);
        boolean b2 = ExprEvalUtils.obj2bool(right);
        return b1 != b2 ? LiteralBoolean.TRUE : LiteralBoolean.FALSE;
    }

    @Override
    public void accept(SQLASTVisitor visitor) {
        visitor.visit(this);
    }
}
