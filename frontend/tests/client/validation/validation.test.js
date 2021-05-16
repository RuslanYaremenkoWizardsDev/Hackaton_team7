import "regenerator-runtime/runtime";
import {validateLogin, validatePassword, validateEmail} from "../../../client/validation/validation";


describe("validateLogin", function () {
    it("should be defined ", function () {
      expect(validateLogin).toBeDefined();
    });
    it("should be function", function () {
      expect(typeof validateLogin).toBe("function");
    });
    it("should be without arguments", function () {
      expect(validateLogin()).toBe(false);
    });
    it("should be valid login", function () {
        const log1 = "AhetYher";
        const log2 = "Ahetyher2465";
        expect(validateLogin(log1)).toBe(true);
        expect(validateLogin(log2)).toBe(true);
    });
    it("should be dont valid login", function () {
        const log1 = "*^%6638";
        const log2 = "";
        const log3 = "er";
        expect(validateLogin(log1)).toBe(false);
        expect(validateLogin(log2)).toBe(false);
        expect(validateLogin(log3)).toBe(false);
    });
})

describe("validatePassword", function () {
    it("should be defined ", function () {
      expect(validatePassword).toBeDefined();
    });
    it("should be function", function () {
      expect(typeof validatePassword).toBe("function");
    });
    it("should be without arguments", function () {
      expect(validatePassword()).toBe(false);
    });
    it("should be valid pass", function () {
        const pass1 = "AhetYher";
        const pass2 = "Ahetyher2465";
        expect(validatePassword(pass1)).toBe(true);
        expect(validatePassword(pass2)).toBe(true);
    });
    it("should be dont valid pass", function () {
        const pass1 = "*^%6638";
        const pass2 = "";
        const pass3 = "er";
        expect(validatePassword(pass1)).toBe(false);
        expect(validatePassword(pass2)).toBe(false);
        expect(validatePassword(pass3)).toBe(false);
    });
})

describe("validateEmail", function () {
    it("should be defined ", function () {
      expect(validateEmail).toBeDefined();
    });
    it("should be function", function () {
      expect(typeof validateEmail).toBe("function");
    });
    it("should be without arguments", function () {
      expect(validateEmail()).toBe(false);
    });
    it("should be valid email", function () {
        const email1 = "AhetYher@com.ua";
        const email2 = "Ahetyher2465@com.ua";
        const email3 = "Ahe_t-yh.er2465@com.ua";
        expect(validateEmail(email1)).toBe(true);
        expect(validateEmail(email2)).toBe(true);
        expect(validateEmail(email3)).toBe(true);
    });
    it("should be dont valid email", function () {
        const email1 = "*^%6638";
        const email2 = "";
        const email3 = "er";
        const email4 = "erwrfv.com.ua";
        expect(validateEmail(email1)).toBe(false);
        expect(validateEmail(email2)).toBe(false);
        expect(validateEmail(email3)).toBe(false);
        expect(validateEmail(email4)).toBe(false);
    });
});