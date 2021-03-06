/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package goja.app.mvc.render.ftl.shiro;

import freemarker.template.SimpleHash;
import goja.app.mvc.render.ftl.shiro.auth.AuthenticatedTag;
import goja.app.mvc.render.ftl.shiro.auth.GuestTag;
import goja.app.mvc.render.ftl.shiro.auth.NotAuthenticatedTag;
import goja.app.mvc.render.ftl.shiro.auth.PrincipalTag;
import goja.app.mvc.render.ftl.shiro.auth.UserTag;
import goja.app.mvc.render.ftl.shiro.permission.HasPermissionTag;
import goja.app.mvc.render.ftl.shiro.permission.LacksPermissionTag;
import goja.app.mvc.render.ftl.shiro.role.HasAnyRolesTag;
import goja.app.mvc.render.ftl.shiro.role.HasRoleTag;
import goja.app.mvc.render.ftl.shiro.role.LacksRoleTag;

/**
 * <p>
 * Shirio权限验证 Freemarker 标签.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-10-27 10:42 AM
 * @since JDK 1.5
 */
public class ShiroTags extends SimpleHash {

    /**
     * Constructs an empty hash that uses the default wrapper set in
     * {@link freemarker.template.WrappingTemplateModel#setDefaultObjectWrapper(freemarker.template.ObjectWrapper)}.
     */
    public ShiroTags() {
        put("authenticated", new AuthenticatedTag());
        put("guest", new GuestTag());
        put("hasAnyRoles", new HasAnyRolesTag());
        put("hasPermission", new HasPermissionTag());
        put("hasRole", new HasRoleTag());
        put("lacksPermission", new LacksPermissionTag());
        put("lacksRole", new LacksRoleTag());
        put("notAuthenticated", new NotAuthenticatedTag());
        put("principal", new PrincipalTag());
        put("user", new UserTag());
    }
}
