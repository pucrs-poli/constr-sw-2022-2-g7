package br.pucrs.auth.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator extends GenericTranslator {
    private final MessageSource source;

    @Autowired
    public Translator(MessageSource messageSource) {
        source = messageSource;
    }

    @Override
    protected MessageSource source() {
        return source;
    }
}
