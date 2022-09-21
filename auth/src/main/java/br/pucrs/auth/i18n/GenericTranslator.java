package br.pucrs.auth.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public abstract class GenericTranslator {
    public GenericTranslator() {
    }

    public String toLocale(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.toLocaleDefault(code, locale, args);
    }

    public String toLocaleDefault(String code, Locale locale, Object... args) {
        return this.source().getMessage(code, args, locale);
    }

    protected abstract MessageSource source();
}
