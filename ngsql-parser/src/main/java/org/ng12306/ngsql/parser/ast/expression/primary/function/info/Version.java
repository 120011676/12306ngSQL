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
package org.ng12306.ngsql.parser.ast.expression.primary.function.info;

import java.util.List;

import org.ng12306.ngsql.parser.ast.expression.Expression;
import org.ng12306.ngsql.parser.ast.expression.primary.function.FunctionExpression;


/**
 * 
* [添加说明]
* @author: <a href="mailto:lvbomr@gmail.com">lvbo</a>
* @date: 2013-5-26 上午7:18:15
* @version: 1.0
 */
public class Version extends FunctionExpression {
    public Version(List<Expression> arguments) {
        super("VERSION", arguments);
    }

    @Override
    public FunctionExpression constructFunction(List<Expression> arguments) {
        return new Version(arguments);
    }

}
