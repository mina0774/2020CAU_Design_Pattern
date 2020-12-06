package com.holub.text;

interface Visitor {
	void visit(RegexToken regexToken);
	void visit(WordToken wordToken);
	void visit(SimpleToken simpleToken);
	void visit(BeginToken beginToken);
}
