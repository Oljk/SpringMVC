<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<div id="ChekInAll">
    <section>
        <h1><span>Registrierung für die</span>  Stars</h1>
        <div id="CenterPChek">Ein interessantes, leichtes und neuartiges Weg zu Ihren Fans näher zu sein!</div>
    </section>
    <section>
        <div id="ChekBlock">
            <form method="POST" id="formregistration">
                <div></div>
                <div id="BlockName1"><p>Vorname, Nachname</p><input type="text" class="validform" name="Name" placeholder="Geben Sie Ihren vollständigen Namen an"/></div>

                <div id="BlockAlias1"><p>Email</p><input  type="text" class="validform" id="Email" name="Email" placeholder="Geben Sie Ihre E-Mail Adresse an"/><span id="validEmail"></span><div id="emailvalid" class="validmessage"></div></div>

                <div id="Phone"><p>Handynummer</p><input type="text" class="validform" id="PhoneRegistration" name="Phone" placeholder="Geben Sie Ihr Handynummer an"/><div id="phonevalid" class="validmessage"></div></div>

                <div id="Login"><p>Login</p><input  type="text" class="validform" name="Login" placeholder="Geben Sie Ihren Benutzernamen an"/><span id="validLogin"></span><div id="loginvalid" class="validmessage"></div></div>

                <div id="Password"><p>Passwort</p><input  class="validform" type="password" name="Password" placeholder="Geben Sie Ihr Passwort an"/><span id="validPassword1"></span><div id="passvalid" class="validmessage"></div></div>

                <div id="PasswordReply"><p>Neues Passwort wiederholen</p><input  class="validform" type="password" id="PassRep" name="PasswordReply" placeholder="Bestätigen Sie Ihr Passwort"/><span id="validPassword"></span><div id="passrepvalid" class="validmessage"></div></div>
                <input class="btn btn-lg btn-success btn-block"
                       type="submit" value="Register">
            </form>
    </section>
</div>
<
