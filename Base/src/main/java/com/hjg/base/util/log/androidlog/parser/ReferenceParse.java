package com.hjg.base.util.log.androidlog.parser;

import com.hjg.base.util.log.Parser;
import com.hjg.base.util.log.utils.ObjectUtil;

import java.lang.ref.Reference;

/**
 * Created by pengwei on 16/3/22.
 */
class ReferenceParse implements Parser<Reference> {
    @Override
    public Class<Reference> parseClassType() {
        return Reference.class;
    }

    @Override
    public String parseString(Reference reference) {
        Object actual = reference.get();
        if (actual == null) {
            return "get reference = null";
        }
        String result = reference.getClass().getSimpleName() + "<"
                + actual.getClass().getSimpleName() + "> {" + "→" + ObjectUtil.objectToString(actual);
        return result + "}";
    }
}
