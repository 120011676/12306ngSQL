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
package org.ng12306.ngsql.parser.recognizer.syntax;

import static org.ng12306.ngsql.parser.recognizer.Token.PUNC_COMMA;
import static org.ng12306.ngsql.parser.recognizer.Token.PUNC_LEFT_PAREN;
import static org.ng12306.ngsql.parser.recognizer.Token.PUNC_RIGHT_PAREN;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.ng12306.ngsql.parser.ast.expression.Expression;
import org.ng12306.ngsql.parser.ast.expression.primary.RowExpression;
import org.ng12306.ngsql.parser.recognizer.lexer.SQLLexer;



/**
 * 
* [添加说明]
* @author: <a href="mailto:lvbomr@gmail.com">lvbo</a>
* @date: 2013-5-26 下午12:03:12
* @version: 1.0
 */
public abstract class MySQLDMLInsertReplaceParser extends MySQLDMLParser {
    public MySQLDMLInsertReplaceParser(SQLLexer lexer, MySQLExprParser exprParser) {
        super(lexer, exprParser);
    }

    protected List<RowExpression> rowList() throws SQLSyntaxErrorException {
        List<RowExpression> valuesList;
        List<Expression> tempRowValue = rowValue();
        if (lexer.token() == PUNC_COMMA) {
            valuesList = new LinkedList<RowExpression>();
            valuesList.add(new RowExpression(tempRowValue));
            for (; lexer.token() == PUNC_COMMA;) {
                lexer.nextToken();
                tempRowValue = rowValue();
                valuesList.add(new RowExpression(tempRowValue));
            }
        } else {
            valuesList = new ArrayList<RowExpression>(1);
            valuesList.add(new RowExpression(tempRowValue));
        }
        return valuesList;
    }

    /**
     * first token is <code>(</code>
     */
    private List<Expression> rowValue() throws SQLSyntaxErrorException {
        match(PUNC_LEFT_PAREN);
        if (lexer.token() == PUNC_RIGHT_PAREN) {
            return Collections.emptyList();
        }
        List<Expression> row;
        Expression expr = exprParser.expression();
        if (lexer.token() == PUNC_COMMA) {
            row = new LinkedList<Expression>();
            row.add(expr);
            for (; lexer.token() == PUNC_COMMA;) {
                lexer.nextToken();
                expr = exprParser.expression();
                row.add(expr);
            }
        } else {
            row = new ArrayList<Expression>(1);
            row.add(expr);
        }
        match(PUNC_RIGHT_PAREN);
        return row;
    }
}
