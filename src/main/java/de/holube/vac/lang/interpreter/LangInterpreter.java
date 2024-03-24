package de.holube.vac.lang.interpreter;

import de.holube.vac.lang.LangBaseListener;
import de.holube.vac.lang.LangParser;

public class LangInterpreter extends LangBaseListener {

    @Override
    public void enterPrint(LangParser.PrintContext ctx) {
        super.enterPrint(ctx);
        String text = ctx.STRING().getText();
        System.out.println(text.substring(1, text.length() - 1));
    }

}
