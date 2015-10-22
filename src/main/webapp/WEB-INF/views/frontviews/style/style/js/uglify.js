
function getURLParameter(t) {
    return decodeURI((RegExp(t + "=(.+?)(&|$)").exec(location.search) || [ , null ])[1]);
}

function toggleHide(t) {
    t.hasClass("hide") ? t.removeClass("hide") : t.addClass("hide");
}

function isMainappOffline() {
    return (void 0 === mainapp_offline || null === mainapp_offline) && (mainapp_offline = !1),
        mainapp_offline;
}

function redirectIfMainappOffline(t) {
    return isMainappOffline() ? (t.preventDefault(), location.href = "/maintenance/",
        !0) : !1;
}

function showWarning() {
    $("body").prepend('<div class="modal__overlay" id="name-modal"><div class="modal__box"><a class="close_warning" id="close_warning" href="#">X</a><div class="modal__content"><p><a href="#">Read more about our rebrand. </a></p></div></div></div>');
}

function Router() {
    _this = this, this.routes = [], riot.route(function() {
        _this.goTo(arguments);
    });
}

function Greenhouse() {}

function App() {
    this.api = new Greenhouse();
}

!function(t, e) {
    function n(t) {
        var e = t.length, n = ut.type(t);
        return ut.isWindow(t) ? !1 : 1 === t.nodeType && e ? !0 : "array" === n || "function" !== n && (0 === e || "number" == typeof e && e > 0 && e - 1 in t);
    }
    function i(t) {
        var e = kt[t] = {};
        return ut.each(t.match(lt) || [], function(t, n) {
            e[n] = !0;
        }), e;
    }
    function r(t, n, i, r) {
        if (ut.acceptData(t)) {
            var o, s, a = ut.expando, u = "string" == typeof n, c = t.nodeType, l = c ? ut.cache : t, h = c ? t[a] : t[a] && a;
            if (h && l[h] && (r || l[h].data) || !u || i !== e) return h || (c ? t[a] = h = K.pop() || ut.guid++ : h = a),
            l[h] || (l[h] = {}, c || (l[h].toJSON = ut.noop)), ("object" == typeof n || "function" == typeof n) && (r ? l[h] = ut.extend(l[h], n) : l[h].data = ut.extend(l[h].data, n)),
                o = l[h], r || (o.data || (o.data = {}), o = o.data), i !== e && (o[ut.camelCase(n)] = i),
                u ? (s = o[n], null == s && (s = o[ut.camelCase(n)])) : s = o, s;
        }
    }
    function o(t, e, n) {
        if (ut.acceptData(t)) {
            var i, r, o, s = t.nodeType, u = s ? ut.cache : t, c = s ? t[ut.expando] : ut.expando;
            if (u[c]) {
                if (e && (o = n ? u[c] : u[c].data)) {
                    ut.isArray(e) ? e = e.concat(ut.map(e, ut.camelCase)) : e in o ? e = [ e ] : (e = ut.camelCase(e),
                        e = e in o ? [ e ] : e.split(" "));
                    for (i = 0, r = e.length; r > i; i++) delete o[e[i]];
                    if (!(n ? a : ut.isEmptyObject)(o)) return;
                }
                (n || (delete u[c].data, a(u[c]))) && (s ? ut.cleanData([ t ], !0) : ut.support.deleteExpando || u != u.window ? delete u[c] : u[c] = null);
            }
        }
    }
    function s(t, n, i) {
        if (i === e && 1 === t.nodeType) {
            var r = "data-" + n.replace(Et, "-$1").toLowerCase();
            if (i = t.getAttribute(r), "string" == typeof i) {
                try {
                    i = "true" === i ? !0 : "false" === i ? !1 : "null" === i ? null : +i + "" === i ? +i : Ct.test(i) ? ut.parseJSON(i) : i;
                } catch (o) {}
                ut.data(t, n, i);
            } else i = e;
        }
        return i;
    }
    function a(t) {
        var e;
        for (e in t) if (("data" !== e || !ut.isEmptyObject(t[e])) && "toJSON" !== e) return !1;
        return !0;
    }
    function u() {
        return !0;
    }
    function c() {
        return !1;
    }
    function l(t, e) {
        do t = t[e]; while (t && 1 !== t.nodeType);
        return t;
    }
    function h(t, e, n) {
        if (e = e || 0, ut.isFunction(e)) return ut.grep(t, function(t, i) {
            var r = !!e.call(t, i, t);
            return r === n;
        });
        if (e.nodeType) return ut.grep(t, function(t) {
            return t === e === n;
        });
        if ("string" == typeof e) {
            var i = ut.grep(t, function(t) {
                return 1 === t.nodeType;
            });
            if (Ht.test(e)) return ut.filter(e, i, !n);
            e = ut.filter(e, i);
        }
        return ut.grep(t, function(t) {
            return ut.inArray(t, e) >= 0 === n;
        });
    }
    function f(t) {
        var e = Wt.split("|"), n = t.createDocumentFragment();
        if (n.createElement) for (;e.length; ) n.createElement(e.pop());
        return n;
    }
    function d(t, e) {
        return t.getElementsByTagName(e)[0] || t.appendChild(t.ownerDocument.createElement(e));
    }
    function p(t) {
        var e = t.getAttributeNode("type");
        return t.type = (e && e.specified) + "/" + t.type, t;
    }
    function m(t) {
        var e = re.exec(t.type);
        return e ? t.type = e[1] : t.removeAttribute("type"), t;
    }
    function g(t, e) {
        for (var n, i = 0; null != (n = t[i]); i++) ut._data(n, "globalEval", !e || ut._data(e[i], "globalEval"));
    }
    function v(t, e) {
        if (1 === e.nodeType && ut.hasData(t)) {
            var n, i, r, o = ut._data(t), s = ut._data(e, o), a = o.events;
            if (a) {
                delete s.handle, s.events = {};
                for (n in a) for (i = 0, r = a[n].length; r > i; i++) ut.event.add(e, n, a[n][i]);
            }
            s.data && (s.data = ut.extend({}, s.data));
        }
    }
    function y(t, e) {
        var n, i, r;
        if (1 === e.nodeType) {
            if (n = e.nodeName.toLowerCase(), !ut.support.noCloneEvent && e[ut.expando]) {
                r = ut._data(e);
                for (i in r.events) ut.removeEvent(e, i, r.handle);
                e.removeAttribute(ut.expando);
            }
            "script" === n && e.text !== t.text ? (p(e).text = t.text, m(e)) : "object" === n ? (e.parentNode && (e.outerHTML = t.outerHTML),
            ut.support.html5Clone && t.innerHTML && !ut.trim(e.innerHTML) && (e.innerHTML = t.innerHTML)) : "input" === n && ee.test(t.type) ? (e.defaultChecked = e.checked = t.checked,
            e.value !== t.value && (e.value = t.value)) : "option" === n ? e.defaultSelected = e.selected = t.defaultSelected : ("input" === n || "textarea" === n) && (e.defaultValue = t.defaultValue);
        }
    }
    function b(t, n) {
        var i, r, o = 0, s = typeof t.getElementsByTagName !== X ? t.getElementsByTagName(n || "*") : typeof t.querySelectorAll !== X ? t.querySelectorAll(n || "*") : e;
        if (!s) for (s = [], i = t.childNodes || t; null != (r = i[o]); o++) !n || ut.nodeName(r, n) ? s.push(r) : ut.merge(s, b(r, n));
        return n === e || n && ut.nodeName(t, n) ? ut.merge([ t ], s) : s;
    }
    function x(t) {
        ee.test(t.type) && (t.defaultChecked = t.checked);
    }
    function w(t, e) {
        if (e in t) return e;
        for (var n = e.charAt(0).toUpperCase() + e.slice(1), i = e, r = Ce.length; r--; ) if (e = Ce[r] + n,
            e in t) return e;
        return i;
    }
    function _(t, e) {
        return t = e || t, "none" === ut.css(t, "display") || !ut.contains(t.ownerDocument, t);
    }
    function k(t, e) {
        for (var n, i, r, o = [], s = 0, a = t.length; a > s; s++) i = t[s], i.style && (o[s] = ut._data(i, "olddisplay"),
            n = i.style.display, e ? (o[s] || "none" !== n || (i.style.display = ""), "" === i.style.display && _(i) && (o[s] = ut._data(i, "olddisplay", A(i.nodeName)))) : o[s] || (r = _(i),
        (n && "none" !== n || !r) && ut._data(i, "olddisplay", r ? n : ut.css(i, "display"))));
        for (s = 0; a > s; s++) i = t[s], i.style && (e && "none" !== i.style.display && "" !== i.style.display || (i.style.display = e ? o[s] || "" : "none"));
        return t;
    }
    function C(t, e, n) {
        var i = ve.exec(e);
        return i ? Math.max(0, i[1] - (n || 0)) + (i[2] || "px") : e;
    }
    function E(t, e, n, i, r) {
        for (var o = n === (i ? "border" : "content") ? 4 : "width" === e ? 1 : 0, s = 0; 4 > o; o += 2) "margin" === n && (s += ut.css(t, n + ke[o], !0, r)),
            i ? ("content" === n && (s -= ut.css(t, "padding" + ke[o], !0, r)), "margin" !== n && (s -= ut.css(t, "border" + ke[o] + "Width", !0, r))) : (s += ut.css(t, "padding" + ke[o], !0, r),
            "padding" !== n && (s += ut.css(t, "border" + ke[o] + "Width", !0, r)));
        return s;
    }
    function T(t, e, n) {
        var i = !0, r = "width" === e ? t.offsetWidth : t.offsetHeight, o = le(t), s = ut.support.boxSizing && "border-box" === ut.css(t, "boxSizing", !1, o);
        if (0 >= r || null == r) {
            if (r = he(t, e, o), (0 > r || null == r) && (r = t.style[e]), ye.test(r)) return r;
            i = s && (ut.support.boxSizingReliable || r === t.style[e]), r = parseFloat(r) || 0;
        }
        return r + E(t, e, n || (s ? "border" : "content"), i, o) + "px";
    }
    function A(t) {
        var e = G, n = xe[t];
        return n || (n = N(t, e), "none" !== n && n || (ce = (ce || ut("<iframe frameborder='0' width='0' height='0'/>").css("cssText", "display:block !important")).appendTo(e.documentElement),
            e = (ce[0].contentWindow || ce[0].contentDocument).document, e.write("<!doctype html><html><body>"),
            e.close(), n = N(t, e), ce.detach()), xe[t] = n), n;
    }
    function N(t, e) {
        var n = ut(e.createElement(t)).appendTo(e.body), i = ut.css(n[0], "display");
        return n.remove(), i;
    }
    function S(t, e, n, i) {
        var r;
        if (ut.isArray(e)) ut.each(e, function(e, r) {
            n || Te.test(t) ? i(t, r) : S(t + "[" + ("object" == typeof r ? e : "") + "]", r, n, i);
        }); else if (n || "object" !== ut.type(e)) i(t, e); else for (r in e) S(t + "[" + r + "]", e[r], n, i);
    }
    function F(t) {
        return function(e, n) {
            "string" != typeof e && (n = e, e = "*");
            var i, r = 0, o = e.toLowerCase().match(lt) || [];
            if (ut.isFunction(n)) for (;i = o[r++]; ) "+" === i[0] ? (i = i.slice(1) || "*",
                (t[i] = t[i] || []).unshift(n)) : (t[i] = t[i] || []).push(n);
        };
    }
    function $(t, n, i, r) {
        function o(u) {
            var c;
            return s[u] = !0, ut.each(t[u] || [], function(t, u) {
                var l = u(n, i, r);
                return "string" != typeof l || a || s[l] ? a ? !(c = l) : e : (n.dataTypes.unshift(l),
                    o(l), !1);
            }), c;
        }
        var s = {}, a = t === He;
        return o(n.dataTypes[0]) || !s["*"] && o("*");
    }
    function j(t, n) {
        var i, r, o = ut.ajaxSettings.flatOptions || {};
        for (r in n) n[r] !== e && ((o[r] ? t : i || (i = {}))[r] = n[r]);
        return i && ut.extend(!0, t, i), t;
    }
    function M(t, n, i) {
        var r, o, s, a, u = t.contents, c = t.dataTypes, l = t.responseFields;
        for (a in l) a in i && (n[l[a]] = i[a]);
        for (;"*" === c[0]; ) c.shift(), o === e && (o = t.mimeType || n.getResponseHeader("Content-Type"));
        if (o) for (a in u) if (u[a] && u[a].test(o)) {
            c.unshift(a);
            break;
        }
        if (c[0] in i) s = c[0]; else {
            for (a in i) {
                if (!c[0] || t.converters[a + " " + c[0]]) {
                    s = a;
                    break;
                }
                r || (r = a);
            }
            s = s || r;
        }
        return s ? (s !== c[0] && c.unshift(s), i[s]) : e;
    }
    function D(t, e) {
        var n, i, r, o, s = {}, a = 0, u = t.dataTypes.slice(), c = u[0];
        if (t.dataFilter && (e = t.dataFilter(e, t.dataType)), u[1]) for (r in t.converters) s[r.toLowerCase()] = t.converters[r];
        for (;i = u[++a]; ) if ("*" !== i) {
            if ("*" !== c && c !== i) {
                if (r = s[c + " " + i] || s["* " + i], !r) for (n in s) if (o = n.split(" "), o[1] === i && (r = s[c + " " + o[0]] || s["* " + o[0]])) {
                    r === !0 ? r = s[n] : s[n] !== !0 && (i = o[0], u.splice(a--, 0, i));
                    break;
                }
                if (r !== !0) if (r && t["throws"]) e = r(e); else try {
                    e = r(e);
                } catch (l) {
                    return {
                        state: "parsererror",
                        error: r ? l : "No conversion from " + c + " to " + i
                    };
                }
            }
            c = i;
        }
        return {
            state: "success",
            data: e
        };
    }
    function L() {
        try {
            return new t.XMLHttpRequest();
        } catch (e) {}
    }
    function O() {
        try {
            return new t.ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {}
    }
    function P() {
        return setTimeout(function() {
            Qe = e;
        }), Qe = ut.now();
    }
    function B(t, e) {
        ut.each(e, function(e, n) {
            for (var i = (on[e] || []).concat(on["*"]), r = 0, o = i.length; o > r; r++) if (i[r].call(t, e, n)) return;
        });
    }
    function R(t, e, n) {
        var i, r, o = 0, s = rn.length, a = ut.Deferred().always(function() {
            delete u.elem;
        }), u = function() {
            if (r) return !1;
            for (var e = Qe || P(), n = Math.max(0, c.startTime + c.duration - e), i = n / c.duration || 0, o = 1 - i, s = 0, u = c.tweens.length; u > s; s++) c.tweens[s].run(o);
            return a.notifyWith(t, [ c, o, n ]), 1 > o && u ? n : (a.resolveWith(t, [ c ]),
                !1);
        }, c = a.promise({
            elem: t,
            props: ut.extend({}, e),
            opts: ut.extend(!0, {
                specialEasing: {}
            }, n),
            originalProperties: e,
            originalOptions: n,
            startTime: Qe || P(),
            duration: n.duration,
            tweens: [],
            createTween: function(e, n) {
                var i = ut.Tween(t, c.opts, e, n, c.opts.specialEasing[e] || c.opts.easing);
                return c.tweens.push(i), i;
            },
            stop: function(e) {
                var n = 0, i = e ? c.tweens.length : 0;
                if (r) return this;
                for (r = !0; i > n; n++) c.tweens[n].run(1);
                return e ? a.resolveWith(t, [ c, e ]) : a.rejectWith(t, [ c, e ]), this;
            }
        }), l = c.props;
        for (z(l, c.opts.specialEasing); s > o; o++) if (i = rn[o].call(c, t, l, c.opts)) return i;
        return B(c, l), ut.isFunction(c.opts.start) && c.opts.start.call(t, c), ut.fx.timer(ut.extend(u, {
            elem: t,
            anim: c,
            queue: c.opts.queue
        })), c.progress(c.opts.progress).done(c.opts.done, c.opts.complete).fail(c.opts.fail).always(c.opts.always);
    }
    function z(t, e) {
        var n, i, r, o, s;
        for (r in t) if (i = ut.camelCase(r), o = e[i], n = t[r], ut.isArray(n) && (o = n[1],
                n = t[r] = n[0]), r !== i && (t[i] = n, delete t[r]), s = ut.cssHooks[i], s && "expand" in s) {
            n = s.expand(n), delete t[i];
            for (r in n) r in t || (t[r] = n[r], e[r] = o);
        } else e[i] = o;
    }
    function I(t, e, n) {
        var i, r, o, s, a, u, c, l, h, f = this, d = t.style, p = {}, m = [], g = t.nodeType && _(t);
        n.queue || (l = ut._queueHooks(t, "fx"), null == l.unqueued && (l.unqueued = 0,
            h = l.empty.fire, l.empty.fire = function() {
            l.unqueued || h();
        }), l.unqueued++, f.always(function() {
            f.always(function() {
                l.unqueued--, ut.queue(t, "fx").length || l.empty.fire();
            });
        })), 1 === t.nodeType && ("height" in e || "width" in e) && (n.overflow = [ d.overflow, d.overflowX, d.overflowY ],
        "inline" === ut.css(t, "display") && "none" === ut.css(t, "float") && (ut.support.inlineBlockNeedsLayout && "inline" !== A(t.nodeName) ? d.zoom = 1 : d.display = "inline-block")),
        n.overflow && (d.overflow = "hidden", ut.support.shrinkWrapBlocks || f.always(function() {
            d.overflow = n.overflow[0], d.overflowX = n.overflow[1], d.overflowY = n.overflow[2];
        }));
        for (r in e) if (s = e[r], tn.exec(s)) {
            if (delete e[r], u = u || "toggle" === s, s === (g ? "hide" : "show")) continue;
            m.push(r);
        }
        if (o = m.length) {
            a = ut._data(t, "fxshow") || ut._data(t, "fxshow", {}), "hidden" in a && (g = a.hidden),
            u && (a.hidden = !g), g ? ut(t).show() : f.done(function() {
                ut(t).hide();
            }), f.done(function() {
                var e;
                ut._removeData(t, "fxshow");
                for (e in p) ut.style(t, e, p[e]);
            });
            for (r = 0; o > r; r++) i = m[r], c = f.createTween(i, g ? a[i] : 0), p[i] = a[i] || ut.style(t, i),
            i in a || (a[i] = c.start, g && (c.end = c.start, c.start = "width" === i || "height" === i ? 1 : 0));
        }
    }
    function q(t, e, n, i, r) {
        return new q.prototype.init(t, e, n, i, r);
    }
    function H(t, e) {
        var n, i = {
            height: t
        }, r = 0;
        for (e = e ? 1 : 0; 4 > r; r += 2 - e) n = ke[r], i["margin" + n] = i["padding" + n] = t;
        return e && (i.opacity = i.width = t), i;
    }
    function U(t) {
        return ut.isWindow(t) ? t : 9 === t.nodeType ? t.defaultView || t.parentWindow : !1;
    }
    var V, W, X = typeof e, G = t.document, Y = t.location, J = t.jQuery, Z = t.$, Q = {}, K = [], tt = "1.9.1", et = K.concat, nt = K.push, it = K.slice, rt = K.indexOf, ot = Q.toString, st = Q.hasOwnProperty, at = tt.trim, ut = function(t, e) {
        return new ut.fn.init(t, e, W);
    }, ct = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source, lt = /\S+/g, ht = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, ft = /^(?:(<[\w\W]+>)[^>]*|#([\w-]*))$/, dt = /^<(\w+)\s*\/?>(?:<\/\1>|)$/, pt = /^[\],:{}\s]*$/, mt = /(?:^|:|,)(?:\s*\[)+/g, gt = /\\(?:["\\\/bfnrt]|u[\da-fA-F]{4})/g, vt = /"[^"\\\r\n]*"|true|false|null|-?(?:\d+\.|)\d+(?:[eE][+-]?\d+|)/g, yt = /^-ms-/, bt = /-([\da-z])/gi, xt = function(t, e) {
        return e.toUpperCase();
    }, wt = function(t) {
        (G.addEventListener || "load" === t.type || "complete" === G.readyState) && (_t(),
            ut.ready());
    }, _t = function() {
        G.addEventListener ? (G.removeEventListener("DOMContentLoaded", wt, !1), t.removeEventListener("load", wt, !1)) : (G.detachEvent("onreadystatechange", wt),
            t.detachEvent("onload", wt));
    };
    ut.fn = ut.prototype = {
        jquery: tt,
        constructor: ut,
        init: function(t, n, i) {
            var r, o;
            if (!t) return this;
            if ("string" == typeof t) {
                if (r = "<" === t.charAt(0) && ">" === t.charAt(t.length - 1) && t.length >= 3 ? [ null, t, null ] : ft.exec(t),
                    !r || !r[1] && n) return !n || n.jquery ? (n || i).find(t) : this.constructor(n).find(t);
                if (r[1]) {
                    if (n = n instanceof ut ? n[0] : n, ut.merge(this, ut.parseHTML(r[1], n && n.nodeType ? n.ownerDocument || n : G, !0)),
                        dt.test(r[1]) && ut.isPlainObject(n)) for (r in n) ut.isFunction(this[r]) ? this[r](n[r]) : this.attr(r, n[r]);
                    return this;
                }
                if (o = G.getElementById(r[2]), o && o.parentNode) {
                    if (o.id !== r[2]) return i.find(t);
                    this.length = 1, this[0] = o;
                }
                return this.context = G, this.selector = t, this;
            }
            return t.nodeType ? (this.context = this[0] = t, this.length = 1, this) : ut.isFunction(t) ? i.ready(t) : (t.selector !== e && (this.selector = t.selector,
                this.context = t.context), ut.makeArray(t, this));
        },
        selector: "",
        length: 0,
        size: function() {
            return this.length;
        },
        toArray: function() {
            return it.call(this);
        },
        get: function(t) {
            return null == t ? this.toArray() : 0 > t ? this[this.length + t] : this[t];
        },
        pushStack: function(t) {
            var e = ut.merge(this.constructor(), t);
            return e.prevObject = this, e.context = this.context, e;
        },
        each: function(t, e) {
            return ut.each(this, t, e);
        },
        ready: function(t) {
            return ut.ready.promise().done(t), this;
        },
        slice: function() {
            return this.pushStack(it.apply(this, arguments));
        },
        first: function() {
            return this.eq(0);
        },
        last: function() {
            return this.eq(-1);
        },
        eq: function(t) {
            var e = this.length, n = +t + (0 > t ? e : 0);
            return this.pushStack(n >= 0 && e > n ? [ this[n] ] : []);
        },
        map: function(t) {
            return this.pushStack(ut.map(this, function(e, n) {
                return t.call(e, n, e);
            }));
        },
        end: function() {
            return this.prevObject || this.constructor(null);
        },
        push: nt,
        sort: [].sort,
        splice: [].splice
    }, ut.fn.init.prototype = ut.fn, ut.extend = ut.fn.extend = function() {
        var t, n, i, r, o, s, a = arguments[0] || {}, u = 1, c = arguments.length, l = !1;
        for ("boolean" == typeof a && (l = a, a = arguments[1] || {}, u = 2), "object" == typeof a || ut.isFunction(a) || (a = {}),
             c === u && (a = this, --u); c > u; u++) if (null != (o = arguments[u])) for (r in o) t = a[r],
            i = o[r], a !== i && (l && i && (ut.isPlainObject(i) || (n = ut.isArray(i))) ? (n ? (n = !1,
            s = t && ut.isArray(t) ? t : []) : s = t && ut.isPlainObject(t) ? t : {}, a[r] = ut.extend(l, s, i)) : i !== e && (a[r] = i));
        return a;
    }, ut.extend({
        noConflict: function(e) {
            return t.$ === ut && (t.$ = Z), e && t.jQuery === ut && (t.jQuery = J), ut;
        },
        isReady: !1,
        readyWait: 1,
        holdReady: function(t) {
            t ? ut.readyWait++ : ut.ready(!0);
        },
        ready: function(t) {
            if (t === !0 ? !--ut.readyWait : !ut.isReady) {
                if (!G.body) return setTimeout(ut.ready);
                ut.isReady = !0, t !== !0 && --ut.readyWait > 0 || (V.resolveWith(G, [ ut ]), ut.fn.trigger && ut(G).trigger("ready").off("ready"));
            }
        },
        isFunction: function(t) {
            return "function" === ut.type(t);
        },
        isArray: Array.isArray || function(t) {
            return "array" === ut.type(t);
        },
        isWindow: function(t) {
            return null != t && t == t.window;
        },
        isNumeric: function(t) {
            return !isNaN(parseFloat(t)) && isFinite(t);
        },
        type: function(t) {
            return null == t ? t + "" : "object" == typeof t || "function" == typeof t ? Q[ot.call(t)] || "object" : typeof t;
        },
        isPlainObject: function(t) {
            if (!t || "object" !== ut.type(t) || t.nodeType || ut.isWindow(t)) return !1;
            try {
                if (t.constructor && !st.call(t, "constructor") && !st.call(t.constructor.prototype, "isPrototypeOf")) return !1;
            } catch (n) {
                return !1;
            }
            var i;
            for (i in t) ;
            return i === e || st.call(t, i);
        },
        isEmptyObject: function(t) {
            var e;
            for (e in t) return !1;
            return !0;
        },
        error: function(t) {
            throw Error(t);
        },
        parseHTML: function(t, e, n) {
            if (!t || "string" != typeof t) return null;
            "boolean" == typeof e && (n = e, e = !1), e = e || G;
            var i = dt.exec(t), r = !n && [];
            return i ? [ e.createElement(i[1]) ] : (i = ut.buildFragment([ t ], e, r), r && ut(r).remove(),
                ut.merge([], i.childNodes));
        },
        parseJSON: function(n) {
            return t.JSON && t.JSON.parse ? t.JSON.parse(n) : null === n ? n : "string" == typeof n && (n = ut.trim(n),
            n && pt.test(n.replace(gt, "@").replace(vt, "]").replace(mt, ""))) ? Function("return " + n)() : (ut.error("Invalid JSON: " + n),
                e);
        },
        parseXML: function(n) {
            var i, r;
            if (!n || "string" != typeof n) return null;
            try {
                t.DOMParser ? (r = new DOMParser(), i = r.parseFromString(n, "text/xml")) : (i = new ActiveXObject("Microsoft.XMLDOM"),
                    i.async = "false", i.loadXML(n));
            } catch (o) {
                i = e;
            }
            return i && i.documentElement && !i.getElementsByTagName("parsererror").length || ut.error("Invalid XML: " + n),
                i;
        },
        noop: function() {},
        globalEval: function(e) {
            e && ut.trim(e) && (t.execScript || function(e) {
                t.eval.call(t, e);
            })(e);
        },
        camelCase: function(t) {
            return t.replace(yt, "ms-").replace(bt, xt);
        },
        nodeName: function(t, e) {
            return t.nodeName && t.nodeName.toLowerCase() === e.toLowerCase();
        },
        each: function(t, e, i) {
            var r, o = 0, s = t.length, a = n(t);
            if (i) {
                if (a) for (;s > o && (r = e.apply(t[o], i), r !== !1); o++) ; else for (o in t) if (r = e.apply(t[o], i),
                    r === !1) break;
            } else if (a) for (;s > o && (r = e.call(t[o], o, t[o]), r !== !1); o++) ; else for (o in t) if (r = e.call(t[o], o, t[o]),
                r === !1) break;
            return t;
        },
        trim: at && !at.call("﻿ ") ? function(t) {
            return null == t ? "" : at.call(t);
        } : function(t) {
            return null == t ? "" : (t + "").replace(ht, "");
        },
        makeArray: function(t, e) {
            var i = e || [];
            return null != t && (n(Object(t)) ? ut.merge(i, "string" == typeof t ? [ t ] : t) : nt.call(i, t)),
                i;
        },
        inArray: function(t, e, n) {
            var i;
            if (e) {
                if (rt) return rt.call(e, t, n);
                for (i = e.length, n = n ? 0 > n ? Math.max(0, i + n) : n : 0; i > n; n++) if (n in e && e[n] === t) return n;
            }
            return -1;
        },
        merge: function(t, n) {
            var i = n.length, r = t.length, o = 0;
            if ("number" == typeof i) for (;i > o; o++) t[r++] = n[o]; else for (;n[o] !== e; ) t[r++] = n[o++];
            return t.length = r, t;
        },
        grep: function(t, e, n) {
            var i, r = [], o = 0, s = t.length;
            for (n = !!n; s > o; o++) i = !!e(t[o], o), n !== i && r.push(t[o]);
            return r;
        },
        map: function(t, e, i) {
            var r, o = 0, s = t.length, a = n(t), u = [];
            if (a) for (;s > o; o++) r = e(t[o], o, i), null != r && (u[u.length] = r); else for (o in t) r = e(t[o], o, i),
            null != r && (u[u.length] = r);
            return et.apply([], u);
        },
        guid: 1,
        proxy: function(t, n) {
            var i, r, o;
            return "string" == typeof n && (o = t[n], n = t, t = o), ut.isFunction(t) ? (i = it.call(arguments, 2),
                r = function() {
                    return t.apply(n || this, i.concat(it.call(arguments)));
                }, r.guid = t.guid = t.guid || ut.guid++, r) : e;
        },
        access: function(t, n, i, r, o, s, a) {
            var u = 0, c = t.length, l = null == i;
            if ("object" === ut.type(i)) {
                o = !0;
                for (u in i) ut.access(t, n, u, i[u], !0, s, a);
            } else if (r !== e && (o = !0, ut.isFunction(r) || (a = !0), l && (a ? (n.call(t, r),
                    n = null) : (l = n, n = function(t, e, n) {
                    return l.call(ut(t), n);
                })), n)) for (;c > u; u++) n(t[u], i, a ? r : r.call(t[u], u, n(t[u], i)));
            return o ? t : l ? n.call(t) : c ? n(t[0], i) : s;
        },
        now: function() {
            return new Date().getTime();
        }
    }), ut.ready.promise = function(e) {
        if (!V) if (V = ut.Deferred(), "complete" === G.readyState) setTimeout(ut.ready); else if (G.addEventListener) G.addEventListener("DOMContentLoaded", wt, !1),
            t.addEventListener("load", wt, !1); else {
            G.attachEvent("onreadystatechange", wt), t.attachEvent("onload", wt);
            var n = !1;
            try {
                n = null == t.frameElement && G.documentElement;
            } catch (i) {}
            n && n.doScroll && function r() {
                if (!ut.isReady) {
                    try {
                        n.doScroll("left");
                    } catch (t) {
                        return setTimeout(r, 50);
                    }
                    _t(), ut.ready();
                }
            }();
        }
        return V.promise(e);
    }, ut.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function(t, e) {
        Q["[object " + e + "]"] = e.toLowerCase();
    }), W = ut(G);
    var kt = {};
    ut.Callbacks = function(t) {
        t = "string" == typeof t ? kt[t] || i(t) : ut.extend({}, t);
        var n, r, o, s, a, u, c = [], l = !t.once && [], h = function(e) {
            for (r = t.memory && e, o = !0, a = u || 0, u = 0, s = c.length, n = !0; c && s > a; a++) if (c[a].apply(e[0], e[1]) === !1 && t.stopOnFalse) {
                r = !1;
                break;
            }
            n = !1, c && (l ? l.length && h(l.shift()) : r ? c = [] : f.disable());
        }, f = {
            add: function() {
                if (c) {
                    var e = c.length;
                    !function i(e) {
                        ut.each(e, function(e, n) {
                            var r = ut.type(n);
                            "function" === r ? t.unique && f.has(n) || c.push(n) : n && n.length && "string" !== r && i(n);
                        });
                    }(arguments), n ? s = c.length : r && (u = e, h(r));
                }
                return this;
            },
            remove: function() {
                return c && ut.each(arguments, function(t, e) {
                    for (var i; (i = ut.inArray(e, c, i)) > -1; ) c.splice(i, 1), n && (s >= i && s--,
                    a >= i && a--);
                }), this;
            },
            has: function(t) {
                return t ? ut.inArray(t, c) > -1 : !(!c || !c.length);
            },
            empty: function() {
                return c = [], this;
            },
            disable: function() {
                return c = l = r = e, this;
            },
            disabled: function() {
                return !c;
            },
            lock: function() {
                return l = e, r || f.disable(), this;
            },
            locked: function() {
                return !l;
            },
            fireWith: function(t, e) {
                return e = e || [], e = [ t, e.slice ? e.slice() : e ], !c || o && !l || (n ? l.push(e) : h(e)),
                    this;
            },
            fire: function() {
                return f.fireWith(this, arguments), this;
            },
            fired: function() {
                return !!o;
            }
        };
        return f;
    }, ut.extend({
        Deferred: function(t) {
            var e = [ [ "resolve", "done", ut.Callbacks("once memory"), "resolved" ], [ "reject", "fail", ut.Callbacks("once memory"), "rejected" ], [ "notify", "progress", ut.Callbacks("memory") ] ], n = "pending", i = {
                state: function() {
                    return n;
                },
                always: function() {
                    return r.done(arguments).fail(arguments), this;
                },
                then: function() {
                    var t = arguments;
                    return ut.Deferred(function(n) {
                        ut.each(e, function(e, o) {
                            var s = o[0], a = ut.isFunction(t[e]) && t[e];
                            r[o[1]](function() {
                                var t = a && a.apply(this, arguments);
                                t && ut.isFunction(t.promise) ? t.promise().done(n.resolve).fail(n.reject).progress(n.notify) : n[s + "With"](this === i ? n.promise() : this, a ? [ t ] : arguments);
                            });
                        }), t = null;
                    }).promise();
                },
                promise: function(t) {
                    return null != t ? ut.extend(t, i) : i;
                }
            }, r = {};
            return i.pipe = i.then, ut.each(e, function(t, o) {
                var s = o[2], a = o[3];
                i[o[1]] = s.add, a && s.add(function() {
                    n = a;
                }, e[1 ^ t][2].disable, e[2][2].lock), r[o[0]] = function() {
                    return r[o[0] + "With"](this === r ? i : this, arguments), this;
                }, r[o[0] + "With"] = s.fireWith;
            }), i.promise(r), t && t.call(r, r), r;
        },
        when: function(t) {
            var e, n, i, r = 0, o = it.call(arguments), s = o.length, a = 1 !== s || t && ut.isFunction(t.promise) ? s : 0, u = 1 === a ? t : ut.Deferred(), c = function(t, n, i) {
                return function(r) {
                    n[t] = this, i[t] = arguments.length > 1 ? it.call(arguments) : r, i === e ? u.notifyWith(n, i) : --a || u.resolveWith(n, i);
                };
            };
            if (s > 1) for (e = Array(s), n = Array(s), i = Array(s); s > r; r++) o[r] && ut.isFunction(o[r].promise) ? o[r].promise().done(c(r, i, o)).fail(u.reject).progress(c(r, n, e)) : --a;
            return a || u.resolveWith(i, o), u.promise();
        }
    }), ut.support = function() {
        var e, n, i, r, o, s, a, u, c, l, h = G.createElement("div");
        if (h.setAttribute("className", "t"), h.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>",
                n = h.getElementsByTagName("*"), i = h.getElementsByTagName("a")[0], !n || !i || !n.length) return {};
        o = G.createElement("select"), a = o.appendChild(G.createElement("option")), r = h.getElementsByTagName("input")[0],
            i.style.cssText = "top:1px;float:left;opacity:.5", e = {
            getSetAttribute: "t" !== h.className,
            leadingWhitespace: 3 === h.firstChild.nodeType,
            tbody: !h.getElementsByTagName("tbody").length,
            htmlSerialize: !!h.getElementsByTagName("link").length,
            style: /top/.test(i.getAttribute("style")),
            hrefNormalized: "/a" === i.getAttribute("href"),
            opacity: /^0.5/.test(i.style.opacity),
            cssFloat: !!i.style.cssFloat,
            checkOn: !!r.value,
            optSelected: a.selected,
            enctype: !!G.createElement("form").enctype,
            html5Clone: "<:nav></:nav>" !== G.createElement("nav").cloneNode(!0).outerHTML,
            boxModel: "CSS1Compat" === G.compatMode,
            deleteExpando: !0,
            noCloneEvent: !0,
            inlineBlockNeedsLayout: !1,
            shrinkWrapBlocks: !1,
            reliableMarginRight: !0,
            boxSizingReliable: !0,
            pixelPosition: !1
        }, r.checked = !0, e.noCloneChecked = r.cloneNode(!0).checked, o.disabled = !0,
            e.optDisabled = !a.disabled;
        try {
            delete h.test;
        } catch (f) {
            e.deleteExpando = !1;
        }
        r = G.createElement("input"), r.setAttribute("value", ""), e.input = "" === r.getAttribute("value"),
            r.value = "t", r.setAttribute("type", "radio"), e.radioValue = "t" === r.value,
            r.setAttribute("checked", "t"), r.setAttribute("name", "t"), s = G.createDocumentFragment(),
            s.appendChild(r), e.appendChecked = r.checked, e.checkClone = s.cloneNode(!0).cloneNode(!0).lastChild.checked,
        h.attachEvent && (h.attachEvent("onclick", function() {
            e.noCloneEvent = !1;
        }), h.cloneNode(!0).click());
        for (l in {
            submit: !0,
            change: !0,
            focusin: !0
        }) h.setAttribute(u = "on" + l, "t"), e[l + "Bubbles"] = u in t || h.attributes[u].expando === !1;
        return h.style.backgroundClip = "content-box", h.cloneNode(!0).style.backgroundClip = "",
            e.clearCloneStyle = "content-box" === h.style.backgroundClip, ut(function() {
            var n, i, r, o = "padding:0;margin:0;border:0;display:block;box-sizing:content-box;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;", s = G.getElementsByTagName("body")[0];
            s && (n = G.createElement("div"), n.style.cssText = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px;margin-top:1px",
                s.appendChild(n).appendChild(h), h.innerHTML = "<table><tr><td></td><td>t</td></tr></table>",
                r = h.getElementsByTagName("td"), r[0].style.cssText = "padding:0;margin:0;border:0;display:none",
                c = 0 === r[0].offsetHeight, r[0].style.display = "", r[1].style.display = "none",
                e.reliableHiddenOffsets = c && 0 === r[0].offsetHeight, h.innerHTML = "", h.style.cssText = "box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;padding:1px;border:1px;display:block;width:4px;margin-top:1%;position:absolute;top:1%;",
                e.boxSizing = 4 === h.offsetWidth, e.doesNotIncludeMarginInBodyOffset = 1 !== s.offsetTop,
            t.getComputedStyle && (e.pixelPosition = "1%" !== (t.getComputedStyle(h, null) || {}).top,
                e.boxSizingReliable = "4px" === (t.getComputedStyle(h, null) || {
                        width: "4px"
                    }).width, i = h.appendChild(G.createElement("div")), i.style.cssText = h.style.cssText = o,
                i.style.marginRight = i.style.width = "0", h.style.width = "1px", e.reliableMarginRight = !parseFloat((t.getComputedStyle(i, null) || {}).marginRight)),
            typeof h.style.zoom !== X && (h.innerHTML = "", h.style.cssText = o + "width:1px;padding:1px;display:inline;zoom:1",
                e.inlineBlockNeedsLayout = 3 === h.offsetWidth, h.style.display = "block", h.innerHTML = "<div></div>",
                h.firstChild.style.width = "5px", e.shrinkWrapBlocks = 3 !== h.offsetWidth, e.inlineBlockNeedsLayout && (s.style.zoom = 1)),
                s.removeChild(n), n = h = r = i = null);
        }), n = o = s = a = i = r = null, e;
    }();
    var Ct = /(?:\{[\s\S]*\}|\[[\s\S]*\])$/, Et = /([A-Z])/g;
    ut.extend({
        cache: {},
        expando: "jQuery" + (tt + Math.random()).replace(/\D/g, ""),
        noData: {
            embed: !0,
            object: "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000",
            applet: !0
        },
        hasData: function(t) {
            return t = t.nodeType ? ut.cache[t[ut.expando]] : t[ut.expando], !!t && !a(t);
        },
        data: function(t, e, n) {
            return r(t, e, n);
        },
        removeData: function(t, e) {
            return o(t, e);
        },
        _data: function(t, e, n) {
            return r(t, e, n, !0);
        },
        _removeData: function(t, e) {
            return o(t, e, !0);
        },
        acceptData: function(t) {
            if (t.nodeType && 1 !== t.nodeType && 9 !== t.nodeType) return !1;
            var e = t.nodeName && ut.noData[t.nodeName.toLowerCase()];
            return !e || e !== !0 && t.getAttribute("classid") === e;
        }
    }), ut.fn.extend({
        data: function(t, n) {
            var i, r, o = this[0], a = 0, u = null;
            if (t === e) {
                if (this.length && (u = ut.data(o), 1 === o.nodeType && !ut._data(o, "parsedAttrs"))) {
                    for (i = o.attributes; i.length > a; a++) r = i[a].name, r.indexOf("data-") || (r = ut.camelCase(r.slice(5)),
                        s(o, r, u[r]));
                    ut._data(o, "parsedAttrs", !0);
                }
                return u;
            }
            return "object" == typeof t ? this.each(function() {
                ut.data(this, t);
            }) : ut.access(this, function(n) {
                return n === e ? o ? s(o, t, ut.data(o, t)) : null : (this.each(function() {
                    ut.data(this, t, n);
                }), e);
            }, null, n, arguments.length > 1, null, !0);
        },
        removeData: function(t) {
            return this.each(function() {
                ut.removeData(this, t);
            });
        }
    }), ut.extend({
        queue: function(t, n, i) {
            var r;
            return t ? (n = (n || "fx") + "queue", r = ut._data(t, n), i && (!r || ut.isArray(i) ? r = ut._data(t, n, ut.makeArray(i)) : r.push(i)),
            r || []) : e;
        },
        dequeue: function(t, e) {
            e = e || "fx";
            var n = ut.queue(t, e), i = n.length, r = n.shift(), o = ut._queueHooks(t, e), s = function() {
                ut.dequeue(t, e);
            };
            "inprogress" === r && (r = n.shift(), i--), o.cur = r, r && ("fx" === e && n.unshift("inprogress"),
                delete o.stop, r.call(t, s, o)), !i && o && o.empty.fire();
        },
        _queueHooks: function(t, e) {
            var n = e + "queueHooks";
            return ut._data(t, n) || ut._data(t, n, {
                    empty: ut.Callbacks("once memory").add(function() {
                        ut._removeData(t, e + "queue"), ut._removeData(t, n);
                    })
                });
        }
    }), ut.fn.extend({
        queue: function(t, n) {
            var i = 2;
            return "string" != typeof t && (n = t, t = "fx", i--), i > arguments.length ? ut.queue(this[0], t) : n === e ? this : this.each(function() {
                var e = ut.queue(this, t, n);
                ut._queueHooks(this, t), "fx" === t && "inprogress" !== e[0] && ut.dequeue(this, t);
            });
        },
        dequeue: function(t) {
            return this.each(function() {
                ut.dequeue(this, t);
            });
        },
        delay: function(t, e) {
            return t = ut.fx ? ut.fx.speeds[t] || t : t, e = e || "fx", this.queue(e, function(e, n) {
                var i = setTimeout(e, t);
                n.stop = function() {
                    clearTimeout(i);
                };
            });
        },
        clearQueue: function(t) {
            return this.queue(t || "fx", []);
        },
        promise: function(t, n) {
            var i, r = 1, o = ut.Deferred(), s = this, a = this.length, u = function() {
                --r || o.resolveWith(s, [ s ]);
            };
            for ("string" != typeof t && (n = t, t = e), t = t || "fx"; a--; ) i = ut._data(s[a], t + "queueHooks"),
            i && i.empty && (r++, i.empty.add(u));
            return u(), o.promise(n);
        }
    });
    var Tt, At, Nt = /[\t\r\n]/g, St = /\r/g, Ft = /^(?:input|select|textarea|button|object)$/i, $t = /^(?:a|area)$/i, jt = /^(?:checked|selected|autofocus|autoplay|async|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped)$/i, Mt = /^(?:checked|selected)$/i, Dt = ut.support.getSetAttribute, Lt = ut.support.input;
    ut.fn.extend({
        attr: function(t, e) {
            return ut.access(this, ut.attr, t, e, arguments.length > 1);
        },
        removeAttr: function(t) {
            return this.each(function() {
                ut.removeAttr(this, t);
            });
        },
        prop: function(t, e) {
            return ut.access(this, ut.prop, t, e, arguments.length > 1);
        },
        removeProp: function(t) {
            return t = ut.propFix[t] || t, this.each(function() {
                try {
                    this[t] = e, delete this[t];
                } catch (n) {}
            });
        },
        addClass: function(t) {
            var e, n, i, r, o, s = 0, a = this.length, u = "string" == typeof t && t;
            if (ut.isFunction(t)) return this.each(function(e) {
                ut(this).addClass(t.call(this, e, this.className));
            });
            if (u) for (e = (t || "").match(lt) || []; a > s; s++) if (n = this[s], i = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(Nt, " ") : " ")) {
                for (o = 0; r = e[o++]; ) 0 > i.indexOf(" " + r + " ") && (i += r + " ");
                n.className = ut.trim(i);
            }
            return this;
        },
        removeClass: function(t) {
            var e, n, i, r, o, s = 0, a = this.length, u = 0 === arguments.length || "string" == typeof t && t;
            if (ut.isFunction(t)) return this.each(function(e) {
                ut(this).removeClass(t.call(this, e, this.className));
            });
            if (u) for (e = (t || "").match(lt) || []; a > s; s++) if (n = this[s], i = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(Nt, " ") : "")) {
                for (o = 0; r = e[o++]; ) for (;i.indexOf(" " + r + " ") >= 0; ) i = i.replace(" " + r + " ", " ");
                n.className = t ? ut.trim(i) : "";
            }
            return this;
        },
        toggleClass: function(t, e) {
            var n = typeof t, i = "boolean" == typeof e;
            return this.each(ut.isFunction(t) ? function(n) {
                ut(this).toggleClass(t.call(this, n, this.className, e), e);
            } : function() {
                if ("string" === n) for (var r, o = 0, s = ut(this), a = e, u = t.match(lt) || []; r = u[o++]; ) a = i ? a : !s.hasClass(r),
                    s[a ? "addClass" : "removeClass"](r); else (n === X || "boolean" === n) && (this.className && ut._data(this, "__className__", this.className),
                    this.className = this.className || t === !1 ? "" : ut._data(this, "__className__") || "");
            });
        },
        hasClass: function(t) {
            for (var e = " " + t + " ", n = 0, i = this.length; i > n; n++) if (1 === this[n].nodeType && (" " + this[n].className + " ").replace(Nt, " ").indexOf(e) >= 0) return !0;
            return !1;
        },
        val: function(t) {
            var n, i, r, o = this[0];
            return arguments.length ? (r = ut.isFunction(t), this.each(function(n) {
                var o, s = ut(this);
                1 === this.nodeType && (o = r ? t.call(this, n, s.val()) : t, null == o ? o = "" : "number" == typeof o ? o += "" : ut.isArray(o) && (o = ut.map(o, function(t) {
                    return null == t ? "" : t + "";
                })), i = ut.valHooks[this.type] || ut.valHooks[this.nodeName.toLowerCase()], i && "set" in i && i.set(this, o, "value") !== e || (this.value = o));
            })) : o ? (i = ut.valHooks[o.type] || ut.valHooks[o.nodeName.toLowerCase()], i && "get" in i && (n = i.get(o, "value")) !== e ? n : (n = o.value,
                "string" == typeof n ? n.replace(St, "") : null == n ? "" : n)) : void 0;
        }
    }), ut.extend({
        valHooks: {
            option: {
                get: function(t) {
                    var e = t.attributes.value;
                    return !e || e.specified ? t.value : t.text;
                }
            },
            select: {
                get: function(t) {
                    for (var e, n, i = t.options, r = t.selectedIndex, o = "select-one" === t.type || 0 > r, s = o ? null : [], a = o ? r + 1 : i.length, u = 0 > r ? a : o ? r : 0; a > u; u++) if (n = i[u],
                            !(!n.selected && u !== r || (ut.support.optDisabled ? n.disabled : null !== n.getAttribute("disabled")) || n.parentNode.disabled && ut.nodeName(n.parentNode, "optgroup"))) {
                        if (e = ut(n).val(), o) return e;
                        s.push(e);
                    }
                    return s;
                },
                set: function(t, e) {
                    var n = ut.makeArray(e);
                    return ut(t).find("option").each(function() {
                        this.selected = ut.inArray(ut(this).val(), n) >= 0;
                    }), n.length || (t.selectedIndex = -1), n;
                }
            }
        },
        attr: function(t, n, i) {
            var r, o, s, a = t.nodeType;
            return t && 3 !== a && 8 !== a && 2 !== a ? typeof t.getAttribute === X ? ut.prop(t, n, i) : (o = 1 !== a || !ut.isXMLDoc(t),
            o && (n = n.toLowerCase(), r = ut.attrHooks[n] || (jt.test(n) ? At : Tt)), i === e ? r && o && "get" in r && null !== (s = r.get(t, n)) ? s : (typeof t.getAttribute !== X && (s = t.getAttribute(n)),
                null == s ? e : s) : null !== i ? r && o && "set" in r && (s = r.set(t, i, n)) !== e ? s : (t.setAttribute(n, i + ""),
                i) : (ut.removeAttr(t, n), e)) : void 0;
        },
        removeAttr: function(t, e) {
            var n, i, r = 0, o = e && e.match(lt);
            if (o && 1 === t.nodeType) for (;n = o[r++]; ) i = ut.propFix[n] || n, jt.test(n) ? !Dt && Mt.test(n) ? t[ut.camelCase("default-" + n)] = t[i] = !1 : t[i] = !1 : ut.attr(t, n, ""),
                t.removeAttribute(Dt ? n : i);
        },
        attrHooks: {
            type: {
                set: function(t, e) {
                    if (!ut.support.radioValue && "radio" === e && ut.nodeName(t, "input")) {
                        var n = t.value;
                        return t.setAttribute("type", e), n && (t.value = n), e;
                    }
                }
            }
        },
        propFix: {
            tabindex: "tabIndex",
            readonly: "readOnly",
            "for": "htmlFor",
            "class": "className",
            maxlength: "maxLength",
            cellspacing: "cellSpacing",
            cellpadding: "cellPadding",
            rowspan: "rowSpan",
            colspan: "colSpan",
            usemap: "useMap",
            frameborder: "frameBorder",
            contenteditable: "contentEditable"
        },
        prop: function(t, n, i) {
            var r, o, s, a = t.nodeType;
            return t && 3 !== a && 8 !== a && 2 !== a ? (s = 1 !== a || !ut.isXMLDoc(t), s && (n = ut.propFix[n] || n,
                o = ut.propHooks[n]), i !== e ? o && "set" in o && (r = o.set(t, i, n)) !== e ? r : t[n] = i : o && "get" in o && null !== (r = o.get(t, n)) ? r : t[n]) : void 0;
        },
        propHooks: {
            tabIndex: {
                get: function(t) {
                    var n = t.getAttributeNode("tabindex");
                    return n && n.specified ? parseInt(n.value, 10) : Ft.test(t.nodeName) || $t.test(t.nodeName) && t.href ? 0 : e;
                }
            }
        }
    }), At = {
        get: function(t, n) {
            var i = ut.prop(t, n), r = "boolean" == typeof i && t.getAttribute(n), o = "boolean" == typeof i ? Lt && Dt ? null != r : Mt.test(n) ? t[ut.camelCase("default-" + n)] : !!r : t.getAttributeNode(n);
            return o && o.value !== !1 ? n.toLowerCase() : e;
        },
        set: function(t, e, n) {
            return e === !1 ? ut.removeAttr(t, n) : Lt && Dt || !Mt.test(n) ? t.setAttribute(!Dt && ut.propFix[n] || n, n) : t[ut.camelCase("default-" + n)] = t[n] = !0,
                n;
        }
    }, Lt && Dt || (ut.attrHooks.value = {
        get: function(t, n) {
            var i = t.getAttributeNode(n);
            return ut.nodeName(t, "input") ? t.defaultValue : i && i.specified ? i.value : e;
        },
        set: function(t, n, i) {
            return ut.nodeName(t, "input") ? (t.defaultValue = n, e) : Tt && Tt.set(t, n, i);
        }
    }), Dt || (Tt = ut.valHooks.button = {
        get: function(t, n) {
            var i = t.getAttributeNode(n);
            return i && ("id" === n || "name" === n || "coords" === n ? "" !== i.value : i.specified) ? i.value : e;
        },
        set: function(t, n, i) {
            var r = t.getAttributeNode(i);
            return r || t.setAttributeNode(r = t.ownerDocument.createAttribute(i)), r.value = n += "",
                "value" === i || n === t.getAttribute(i) ? n : e;
        }
    }, ut.attrHooks.contenteditable = {
        get: Tt.get,
        set: function(t, e, n) {
            Tt.set(t, "" === e ? !1 : e, n);
        }
    }, ut.each([ "width", "height" ], function(t, n) {
        ut.attrHooks[n] = ut.extend(ut.attrHooks[n], {
            set: function(t, i) {
                return "" === i ? (t.setAttribute(n, "auto"), i) : e;
            }
        });
    })), ut.support.hrefNormalized || (ut.each([ "href", "src", "width", "height" ], function(t, n) {
        ut.attrHooks[n] = ut.extend(ut.attrHooks[n], {
            get: function(t) {
                var i = t.getAttribute(n, 2);
                return null == i ? e : i;
            }
        });
    }), ut.each([ "href", "src" ], function(t, e) {
        ut.propHooks[e] = {
            get: function(t) {
                return t.getAttribute(e, 4);
            }
        };
    })), ut.support.style || (ut.attrHooks.style = {
        get: function(t) {
            return t.style.cssText || e;
        },
        set: function(t, e) {
            return t.style.cssText = e + "";
        }
    }), ut.support.optSelected || (ut.propHooks.selected = ut.extend(ut.propHooks.selected, {
        get: function(t) {
            var e = t.parentNode;
            return e && (e.selectedIndex, e.parentNode && e.parentNode.selectedIndex), null;
        }
    })), ut.support.enctype || (ut.propFix.enctype = "encoding"), ut.support.checkOn || ut.each([ "radio", "checkbox" ], function() {
        ut.valHooks[this] = {
            get: function(t) {
                return null === t.getAttribute("value") ? "on" : t.value;
            }
        };
    }), ut.each([ "radio", "checkbox" ], function() {
        ut.valHooks[this] = ut.extend(ut.valHooks[this], {
            set: function(t, n) {
                return ut.isArray(n) ? t.checked = ut.inArray(ut(t).val(), n) >= 0 : e;
            }
        });
    });
    var Ot = /^(?:input|select|textarea)$/i, Pt = /^key/, Bt = /^(?:mouse|contextmenu)|click/, Rt = /^(?:focusinfocus|focusoutblur)$/, zt = /^([^.]*)(?:\.(.+)|)$/;
    ut.event = {
        global: {},
        add: function(t, n, i, r, o) {
            var s, a, u, c, l, h, f, d, p, m, g, v = ut._data(t);
            if (v) {
                for (i.handler && (c = i, i = c.handler, o = c.selector), i.guid || (i.guid = ut.guid++),
                     (a = v.events) || (a = v.events = {}), (h = v.handle) || (h = v.handle = function(t) {
                    return typeof ut === X || t && ut.event.triggered === t.type ? e : ut.event.dispatch.apply(h.elem, arguments);
                }, h.elem = t), n = (n || "").match(lt) || [ "" ], u = n.length; u--; ) s = zt.exec(n[u]) || [],
                    p = g = s[1], m = (s[2] || "").split(".").sort(), l = ut.event.special[p] || {},
                    p = (o ? l.delegateType : l.bindType) || p, l = ut.event.special[p] || {}, f = ut.extend({
                    type: p,
                    origType: g,
                    data: r,
                    handler: i,
                    guid: i.guid,
                    selector: o,
                    needsContext: o && ut.expr.match.needsContext.test(o),
                    namespace: m.join(".")
                }, c), (d = a[p]) || (d = a[p] = [], d.delegateCount = 0, l.setup && l.setup.call(t, r, m, h) !== !1 || (t.addEventListener ? t.addEventListener(p, h, !1) : t.attachEvent && t.attachEvent("on" + p, h))),
                l.add && (l.add.call(t, f), f.handler.guid || (f.handler.guid = i.guid)), o ? d.splice(d.delegateCount++, 0, f) : d.push(f),
                    ut.event.global[p] = !0;
                t = null;
            }
        },
        remove: function(t, e, n, i, r) {
            var o, s, a, u, c, l, h, f, d, p, m, g = ut.hasData(t) && ut._data(t);
            if (g && (l = g.events)) {
                for (e = (e || "").match(lt) || [ "" ], c = e.length; c--; ) if (a = zt.exec(e[c]) || [],
                        d = m = a[1], p = (a[2] || "").split(".").sort(), d) {
                    for (h = ut.event.special[d] || {}, d = (i ? h.delegateType : h.bindType) || d,
                             f = l[d] || [], a = a[2] && RegExp("(^|\\.)" + p.join("\\.(?:.*\\.|)") + "(\\.|$)"),
                             u = o = f.length; o--; ) s = f[o], !r && m !== s.origType || n && n.guid !== s.guid || a && !a.test(s.namespace) || i && i !== s.selector && ("**" !== i || !s.selector) || (f.splice(o, 1),
                    s.selector && f.delegateCount--, h.remove && h.remove.call(t, s));
                    u && !f.length && (h.teardown && h.teardown.call(t, p, g.handle) !== !1 || ut.removeEvent(t, d, g.handle),
                        delete l[d]);
                } else for (d in l) ut.event.remove(t, d + e[c], n, i, !0);
                ut.isEmptyObject(l) && (delete g.handle, ut._removeData(t, "events"));
            }
        },
        trigger: function(n, i, r, o) {
            var s, a, u, c, l, h, f, d = [ r || G ], p = st.call(n, "type") ? n.type : n, m = st.call(n, "namespace") ? n.namespace.split(".") : [];
            if (u = h = r = r || G, 3 !== r.nodeType && 8 !== r.nodeType && !Rt.test(p + ut.event.triggered) && (p.indexOf(".") >= 0 && (m = p.split("."),
                    p = m.shift(), m.sort()), a = 0 > p.indexOf(":") && "on" + p, n = n[ut.expando] ? n : new ut.Event(p, "object" == typeof n && n),
                    n.isTrigger = !0, n.namespace = m.join("."), n.namespace_re = n.namespace ? RegExp("(^|\\.)" + m.join("\\.(?:.*\\.|)") + "(\\.|$)") : null,
                    n.result = e, n.target || (n.target = r), i = null == i ? [ n ] : ut.makeArray(i, [ n ]),
                    l = ut.event.special[p] || {}, o || !l.trigger || l.trigger.apply(r, i) !== !1)) {
                if (!o && !l.noBubble && !ut.isWindow(r)) {
                    for (c = l.delegateType || p, Rt.test(c + p) || (u = u.parentNode); u; u = u.parentNode) d.push(u),
                        h = u;
                    h === (r.ownerDocument || G) && d.push(h.defaultView || h.parentWindow || t);
                }
                for (f = 0; (u = d[f++]) && !n.isPropagationStopped(); ) n.type = f > 1 ? c : l.bindType || p,
                    s = (ut._data(u, "events") || {})[n.type] && ut._data(u, "handle"), s && s.apply(u, i),
                    s = a && u[a], s && ut.acceptData(u) && s.apply && s.apply(u, i) === !1 && n.preventDefault();
                if (n.type = p, !(o || n.isDefaultPrevented() || l._default && l._default.apply(r.ownerDocument, i) !== !1 || "click" === p && ut.nodeName(r, "a") || !ut.acceptData(r) || !a || !r[p] || ut.isWindow(r))) {
                    h = r[a], h && (r[a] = null), ut.event.triggered = p;
                    try {
                        r[p]();
                    } catch (g) {}
                    ut.event.triggered = e, h && (r[a] = h);
                }
                return n.result;
            }
        },
        dispatch: function(t) {
            t = ut.event.fix(t);
            var n, i, r, o, s, a = [], u = it.call(arguments), c = (ut._data(this, "events") || {})[t.type] || [], l = ut.event.special[t.type] || {};
            if (u[0] = t, t.delegateTarget = this, !l.preDispatch || l.preDispatch.call(this, t) !== !1) {
                for (a = ut.event.handlers.call(this, t, c), n = 0; (o = a[n++]) && !t.isPropagationStopped(); ) for (t.currentTarget = o.elem,
                                                                                                                          s = 0; (r = o.handlers[s++]) && !t.isImmediatePropagationStopped(); ) (!t.namespace_re || t.namespace_re.test(r.namespace)) && (t.handleObj = r,
                    t.data = r.data, i = ((ut.event.special[r.origType] || {}).handle || r.handler).apply(o.elem, u),
                i !== e && (t.result = i) === !1 && (t.preventDefault(), t.stopPropagation()));
                return l.postDispatch && l.postDispatch.call(this, t), t.result;
            }
        },
        handlers: function(t, n) {
            var i, r, o, s, a = [], u = n.delegateCount, c = t.target;
            if (u && c.nodeType && (!t.button || "click" !== t.type)) for (;c != this; c = c.parentNode || this) if (1 === c.nodeType && (c.disabled !== !0 || "click" !== t.type)) {
                for (o = [], s = 0; u > s; s++) r = n[s], i = r.selector + " ", o[i] === e && (o[i] = r.needsContext ? ut(i, this).index(c) >= 0 : ut.find(i, this, null, [ c ]).length),
                o[i] && o.push(r);
                o.length && a.push({
                    elem: c,
                    handlers: o
                });
            }
            return n.length > u && a.push({
                elem: this,
                handlers: n.slice(u)
            }), a;
        },
        fix: function(t) {
            if (t[ut.expando]) return t;
            var e, n, i, r = t.type, o = t, s = this.fixHooks[r];
            for (s || (this.fixHooks[r] = s = Bt.test(r) ? this.mouseHooks : Pt.test(r) ? this.keyHooks : {}),
                     i = s.props ? this.props.concat(s.props) : this.props, t = new ut.Event(o), e = i.length; e--; ) n = i[e],
                t[n] = o[n];
            return t.target || (t.target = o.srcElement || G), 3 === t.target.nodeType && (t.target = t.target.parentNode),
                t.metaKey = !!t.metaKey, s.filter ? s.filter(t, o) : t;
        },
        props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: "char charCode key keyCode".split(" "),
            filter: function(t, e) {
                return null == t.which && (t.which = null != e.charCode ? e.charCode : e.keyCode),
                    t;
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function(t, n) {
                var i, r, o, s = n.button, a = n.fromElement;
                return null == t.pageX && null != n.clientX && (r = t.target.ownerDocument || G,
                    o = r.documentElement, i = r.body, t.pageX = n.clientX + (o && o.scrollLeft || i && i.scrollLeft || 0) - (o && o.clientLeft || i && i.clientLeft || 0),
                    t.pageY = n.clientY + (o && o.scrollTop || i && i.scrollTop || 0) - (o && o.clientTop || i && i.clientTop || 0)),
                !t.relatedTarget && a && (t.relatedTarget = a === t.target ? n.toElement : a), t.which || s === e || (t.which = 1 & s ? 1 : 2 & s ? 3 : 4 & s ? 2 : 0),
                    t;
            }
        },
        special: {
            load: {
                noBubble: !0
            },
            click: {
                trigger: function() {
                    return ut.nodeName(this, "input") && "checkbox" === this.type && this.click ? (this.click(),
                        !1) : e;
                }
            },
            focus: {
                trigger: function() {
                    if (this !== G.activeElement && this.focus) try {
                        return this.focus(), !1;
                    } catch (t) {}
                },
                delegateType: "focusin"
            },
            blur: {
                trigger: function() {
                    return this === G.activeElement && this.blur ? (this.blur(), !1) : e;
                },
                delegateType: "focusout"
            },
            beforeunload: {
                postDispatch: function(t) {
                    t.result !== e && (t.originalEvent.returnValue = t.result);
                }
            }
        },
        simulate: function(t, e, n, i) {
            var r = ut.extend(new ut.Event(), n, {
                type: t,
                isSimulated: !0,
                originalEvent: {}
            });
            i ? ut.event.trigger(r, null, e) : ut.event.dispatch.call(e, r), r.isDefaultPrevented() && n.preventDefault();
        }
    }, ut.removeEvent = G.removeEventListener ? function(t, e, n) {
        t.removeEventListener && t.removeEventListener(e, n, !1);
    } : function(t, e, n) {
        var i = "on" + e;
        t.detachEvent && (typeof t[i] === X && (t[i] = null), t.detachEvent(i, n));
    }, ut.Event = function(t, n) {
        return this instanceof ut.Event ? (t && t.type ? (this.originalEvent = t, this.type = t.type,
            this.isDefaultPrevented = t.defaultPrevented || t.returnValue === !1 || t.getPreventDefault && t.getPreventDefault() ? u : c) : this.type = t,
        n && ut.extend(this, n), this.timeStamp = t && t.timeStamp || ut.now(), this[ut.expando] = !0,
            e) : new ut.Event(t, n);
    }, ut.Event.prototype = {
        isDefaultPrevented: c,
        isPropagationStopped: c,
        isImmediatePropagationStopped: c,
        preventDefault: function() {
            var t = this.originalEvent;
            this.isDefaultPrevented = u, t && (t.preventDefault ? t.preventDefault() : t.returnValue = !1);
        },
        stopPropagation: function() {
            var t = this.originalEvent;
            this.isPropagationStopped = u, t && (t.stopPropagation && t.stopPropagation(), t.cancelBubble = !0);
        },
        stopImmediatePropagation: function() {
            this.isImmediatePropagationStopped = u, this.stopPropagation();
        }
    }, ut.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    }, function(t, e) {
        ut.event.special[t] = {
            delegateType: e,
            bindType: e,
            handle: function(t) {
                var n, i = this, r = t.relatedTarget, o = t.handleObj;
                return (!r || r !== i && !ut.contains(i, r)) && (t.type = o.origType, n = o.handler.apply(this, arguments),
                    t.type = e), n;
            }
        };
    }), ut.support.submitBubbles || (ut.event.special.submit = {
        setup: function() {
            return ut.nodeName(this, "form") ? !1 : (ut.event.add(this, "click._submit keypress._submit", function(t) {
                var n = t.target, i = ut.nodeName(n, "input") || ut.nodeName(n, "button") ? n.form : e;
                i && !ut._data(i, "submitBubbles") && (ut.event.add(i, "submit._submit", function(t) {
                    t._submit_bubble = !0;
                }), ut._data(i, "submitBubbles", !0));
            }), e);
        },
        postDispatch: function(t) {
            t._submit_bubble && (delete t._submit_bubble, this.parentNode && !t.isTrigger && ut.event.simulate("submit", this.parentNode, t, !0));
        },
        teardown: function() {
            return ut.nodeName(this, "form") ? !1 : (ut.event.remove(this, "._submit"), e);
        }
    }), ut.support.changeBubbles || (ut.event.special.change = {
        setup: function() {
            return Ot.test(this.nodeName) ? (("checkbox" === this.type || "radio" === this.type) && (ut.event.add(this, "propertychange._change", function(t) {
                "checked" === t.originalEvent.propertyName && (this._just_changed = !0);
            }), ut.event.add(this, "click._change", function(t) {
                this._just_changed && !t.isTrigger && (this._just_changed = !1), ut.event.simulate("change", this, t, !0);
            })), !1) : (ut.event.add(this, "beforeactivate._change", function(t) {
                var e = t.target;
                Ot.test(e.nodeName) && !ut._data(e, "changeBubbles") && (ut.event.add(e, "change._change", function(t) {
                    !this.parentNode || t.isSimulated || t.isTrigger || ut.event.simulate("change", this.parentNode, t, !0);
                }), ut._data(e, "changeBubbles", !0));
            }), e);
        },
        handle: function(t) {
            var n = t.target;
            return this !== n || t.isSimulated || t.isTrigger || "radio" !== n.type && "checkbox" !== n.type ? t.handleObj.handler.apply(this, arguments) : e;
        },
        teardown: function() {
            return ut.event.remove(this, "._change"), !Ot.test(this.nodeName);
        }
    }), ut.support.focusinBubbles || ut.each({
        focus: "focusin",
        blur: "focusout"
    }, function(t, e) {
        var n = 0, i = function(t) {
            ut.event.simulate(e, t.target, ut.event.fix(t), !0);
        };
        ut.event.special[e] = {
            setup: function() {
                0 === n++ && G.addEventListener(t, i, !0);
            },
            teardown: function() {
                0 === --n && G.removeEventListener(t, i, !0);
            }
        };
    }), ut.fn.extend({
        on: function(t, n, i, r, o) {
            var s, a;
            if ("object" == typeof t) {
                "string" != typeof n && (i = i || n, n = e);
                for (s in t) this.on(s, n, i, t[s], o);
                return this;
            }
            if (null == i && null == r ? (r = n, i = n = e) : null == r && ("string" == typeof n ? (r = i,
                    i = e) : (r = i, i = n, n = e)), r === !1) r = c; else if (!r) return this;
            return 1 === o && (a = r, r = function(t) {
                return ut().off(t), a.apply(this, arguments);
            }, r.guid = a.guid || (a.guid = ut.guid++)), this.each(function() {
                ut.event.add(this, t, r, i, n);
            });
        },
        one: function(t, e, n, i) {
            return this.on(t, e, n, i, 1);
        },
        off: function(t, n, i) {
            var r, o;
            if (t && t.preventDefault && t.handleObj) return r = t.handleObj, ut(t.delegateTarget).off(r.namespace ? r.origType + "." + r.namespace : r.origType, r.selector, r.handler),
                this;
            if ("object" == typeof t) {
                for (o in t) this.off(o, n, t[o]);
                return this;
            }
            return (n === !1 || "function" == typeof n) && (i = n, n = e), i === !1 && (i = c),
                this.each(function() {
                    ut.event.remove(this, t, i, n);
                });
        },
        bind: function(t, e, n) {
            return this.on(t, null, e, n);
        },
        unbind: function(t, e) {
            return this.off(t, null, e);
        },
        delegate: function(t, e, n, i) {
            return this.on(e, t, n, i);
        },
        undelegate: function(t, e, n) {
            return 1 === arguments.length ? this.off(t, "**") : this.off(e, t || "**", n);
        },
        trigger: function(t, e) {
            return this.each(function() {
                ut.event.trigger(t, e, this);
            });
        },
        triggerHandler: function(t, n) {
            var i = this[0];
            return i ? ut.event.trigger(t, n, i, !0) : e;
        }
    }), function(t, e) {
        function n(t) {
            return pt.test(t + "");
        }
        function i() {
            var t, e = [];
            return t = function(n, i) {
                return e.push(n += " ") > C.cacheLength && delete t[e.shift()], t[n] = i;
            };
        }
        function r(t) {
            return t[R] = !0, t;
        }
        function o(t) {
            var e = $.createElement("div");
            try {
                return t(e);
            } catch (n) {
                return !1;
            } finally {
                e = null;
            }
        }
        function s(t, e, n, i) {
            var r, o, s, a, u, c, l, d, p, m;
            if ((e ? e.ownerDocument || e : z) !== $ && F(e), e = e || $, n = n || [], !t || "string" != typeof t) return n;
            if (1 !== (a = e.nodeType) && 9 !== a) return [];
            if (!M && !i) {
                if (r = mt.exec(t)) if (s = r[1]) {
                    if (9 === a) {
                        if (o = e.getElementById(s), !o || !o.parentNode) return n;
                        if (o.id === s) return n.push(o), n;
                    } else if (e.ownerDocument && (o = e.ownerDocument.getElementById(s)) && P(e, o) && o.id === s) return n.push(o),
                        n;
                } else {
                    if (r[2]) return Z.apply(n, Q.call(e.getElementsByTagName(t), 0)), n;
                    if ((s = r[3]) && I.getByClassName && e.getElementsByClassName) return Z.apply(n, Q.call(e.getElementsByClassName(s), 0)),
                        n;
                }
                if (I.qsa && !D.test(t)) {
                    if (l = !0, d = R, p = e, m = 9 === a && t, 1 === a && "object" !== e.nodeName.toLowerCase()) {
                        for (c = h(t), (l = e.getAttribute("id")) ? d = l.replace(yt, "\\$&") : e.setAttribute("id", d),
                                 d = "[id='" + d + "'] ", u = c.length; u--; ) c[u] = d + f(c[u]);
                        p = dt.test(t) && e.parentNode || e, m = c.join(",");
                    }
                    if (m) try {
                        return Z.apply(n, Q.call(p.querySelectorAll(m), 0)), n;
                    } catch (g) {} finally {
                        l || e.removeAttribute("id");
                    }
                }
            }
            return x(t.replace(st, "$1"), e, n, i);
        }
        function a(t, e) {
            var n = e && t, i = n && (~e.sourceIndex || G) - (~t.sourceIndex || G);
            if (i) return i;
            if (n) for (;n = n.nextSibling; ) if (n === e) return -1;
            return t ? 1 : -1;
        }
        function u(t) {
            return function(e) {
                var n = e.nodeName.toLowerCase();
                return "input" === n && e.type === t;
            };
        }
        function c(t) {
            return function(e) {
                var n = e.nodeName.toLowerCase();
                return ("input" === n || "button" === n) && e.type === t;
            };
        }
        function l(t) {
            return r(function(e) {
                return e = +e, r(function(n, i) {
                    for (var r, o = t([], n.length, e), s = o.length; s--; ) n[r = o[s]] && (n[r] = !(i[r] = n[r]));
                });
            });
        }
        function h(t, e) {
            var n, i, r, o, a, u, c, l = V[t + " "];
            if (l) return e ? 0 : l.slice(0);
            for (a = t, u = [], c = C.preFilter; a; ) {
                (!n || (i = at.exec(a))) && (i && (a = a.slice(i[0].length) || a), u.push(r = [])),
                    n = !1, (i = ct.exec(a)) && (n = i.shift(), r.push({
                    value: n,
                    type: i[0].replace(st, " ")
                }), a = a.slice(n.length));
                for (o in C.filter) !(i = ft[o].exec(a)) || c[o] && !(i = c[o](i)) || (n = i.shift(),
                    r.push({
                        value: n,
                        type: o,
                        matches: i
                    }), a = a.slice(n.length));
                if (!n) break;
            }
            return e ? a.length : a ? s.error(t) : V(t, u).slice(0);
        }
        function f(t) {
            for (var e = 0, n = t.length, i = ""; n > e; e++) i += t[e].value;
            return i;
        }
        function d(t, e, n) {
            var i = e.dir, r = n && "parentNode" === i, o = H++;
            return e.first ? function(e, n, o) {
                for (;e = e[i]; ) if (1 === e.nodeType || r) return t(e, n, o);
            } : function(e, n, s) {
                var a, u, c, l = q + " " + o;
                if (s) {
                    for (;e = e[i]; ) if ((1 === e.nodeType || r) && t(e, n, s)) return !0;
                } else for (;e = e[i]; ) if (1 === e.nodeType || r) if (c = e[R] || (e[R] = {}),
                    (u = c[i]) && u[0] === l) {
                    if ((a = u[1]) === !0 || a === k) return a === !0;
                } else if (u = c[i] = [ l ], u[1] = t(e, n, s) || k, u[1] === !0) return !0;
            };
        }
        function p(t) {
            return t.length > 1 ? function(e, n, i) {
                for (var r = t.length; r--; ) if (!t[r](e, n, i)) return !1;
                return !0;
            } : t[0];
        }
        function m(t, e, n, i, r) {
            for (var o, s = [], a = 0, u = t.length, c = null != e; u > a; a++) (o = t[a]) && (!n || n(o, i, r)) && (s.push(o),
            c && e.push(a));
            return s;
        }
        function g(t, e, n, i, o, s) {
            return i && !i[R] && (i = g(i)), o && !o[R] && (o = g(o, s)), r(function(r, s, a, u) {
                var c, l, h, f = [], d = [], p = s.length, g = r || b(e || "*", a.nodeType ? [ a ] : a, []), v = !t || !r && e ? g : m(g, f, t, a, u), y = n ? o || (r ? t : p || i) ? [] : s : v;
                if (n && n(v, y, a, u), i) for (c = m(y, d), i(c, [], a, u), l = c.length; l--; ) (h = c[l]) && (y[d[l]] = !(v[d[l]] = h));
                if (r) {
                    if (o || t) {
                        if (o) {
                            for (c = [], l = y.length; l--; ) (h = y[l]) && c.push(v[l] = h);
                            o(null, y = [], c, u);
                        }
                        for (l = y.length; l--; ) (h = y[l]) && (c = o ? K.call(r, h) : f[l]) > -1 && (r[c] = !(s[c] = h));
                    }
                } else y = m(y === s ? y.splice(p, y.length) : y), o ? o(null, s, y, u) : Z.apply(s, y);
            });
        }
        function v(t) {
            for (var e, n, i, r = t.length, o = C.relative[t[0].type], s = o || C.relative[" "], a = o ? 1 : 0, u = d(function(t) {
                return t === e;
            }, s, !0), c = d(function(t) {
                return K.call(e, t) > -1;
            }, s, !0), l = [ function(t, n, i) {
                return !o && (i || n !== S) || ((e = n).nodeType ? u(t, n, i) : c(t, n, i));
            } ]; r > a; a++) if (n = C.relative[t[a].type]) l = [ d(p(l), n) ]; else {
                if (n = C.filter[t[a].type].apply(null, t[a].matches), n[R]) {
                    for (i = ++a; r > i && !C.relative[t[i].type]; i++) ;
                    return g(a > 1 && p(l), a > 1 && f(t.slice(0, a - 1)).replace(st, "$1"), n, i > a && v(t.slice(a, i)), r > i && v(t = t.slice(i)), r > i && f(t));
                }
                l.push(n);
            }
            return p(l);
        }
        function y(t, e) {
            var n = 0, i = e.length > 0, o = t.length > 0, a = function(r, a, u, c, l) {
                var h, f, d, p = [], g = 0, v = "0", y = r && [], b = null != l, x = S, w = r || o && C.find.TAG("*", l && a.parentNode || a), _ = q += null == x ? 1 : Math.random() || .1;
                for (b && (S = a !== $ && a, k = n); null != (h = w[v]); v++) {
                    if (o && h) {
                        for (f = 0; d = t[f++]; ) if (d(h, a, u)) {
                            c.push(h);
                            break;
                        }
                        b && (q = _, k = ++n);
                    }
                    i && ((h = !d && h) && g--, r && y.push(h));
                }
                if (g += v, i && v !== g) {
                    for (f = 0; d = e[f++]; ) d(y, p, a, u);
                    if (r) {
                        if (g > 0) for (;v--; ) y[v] || p[v] || (p[v] = J.call(c));
                        p = m(p);
                    }
                    Z.apply(c, p), b && !r && p.length > 0 && g + e.length > 1 && s.uniqueSort(c);
                }
                return b && (q = _, S = x), y;
            };
            return i ? r(a) : a;
        }
        function b(t, e, n) {
            for (var i = 0, r = e.length; r > i; i++) s(t, e[i], n);
            return n;
        }
        function x(t, e, n, i) {
            var r, o, s, a, u, c = h(t);
            if (!i && 1 === c.length) {
                if (o = c[0] = c[0].slice(0), o.length > 2 && "ID" === (s = o[0]).type && 9 === e.nodeType && !M && C.relative[o[1].type]) {
                    if (e = C.find.ID(s.matches[0].replace(xt, wt), e)[0], !e) return n;
                    t = t.slice(o.shift().value.length);
                }
                for (r = ft.needsContext.test(t) ? 0 : o.length; r-- && (s = o[r], !C.relative[a = s.type]); ) if ((u = C.find[a]) && (i = u(s.matches[0].replace(xt, wt), dt.test(o[0].type) && e.parentNode || e))) {
                    if (o.splice(r, 1), t = i.length && f(o), !t) return Z.apply(n, Q.call(i, 0)), n;
                    break;
                }
            }
            return A(t, c)(i, e, M, n, dt.test(t)), n;
        }
        function w() {}
        var _, k, C, E, T, A, N, S, F, $, j, M, D, L, O, P, B, R = "sizzle" + -new Date(), z = t.document, I = {}, q = 0, H = 0, U = i(), V = i(), W = i(), X = typeof e, G = 1 << 31, Y = [], J = Y.pop, Z = Y.push, Q = Y.slice, K = Y.indexOf || function(t) {
                for (var e = 0, n = this.length; n > e; e++) if (this[e] === t) return e;
                return -1;
            }, tt = "[\\x20\\t\\r\\n\\f]", et = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+", nt = et.replace("w", "w#"), it = "([*^$|!~]?=)", rt = "\\[" + tt + "*(" + et + ")" + tt + "*(?:" + it + tt + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + nt + ")|)|)" + tt + "*\\]", ot = ":(" + et + ")(?:\\(((['\"])((?:\\\\.|[^\\\\])*?)\\3|((?:\\\\.|[^\\\\()[\\]]|" + rt.replace(3, 8) + ")*)|.*)\\)|)", st = RegExp("^" + tt + "+|((?:^|[^\\\\])(?:\\\\.)*)" + tt + "+$", "g"), at = RegExp("^" + tt + "*," + tt + "*"), ct = RegExp("^" + tt + "*([\\x20\\t\\r\\n\\f>+~])" + tt + "*"), lt = RegExp(ot), ht = RegExp("^" + nt + "$"), ft = {
            ID: RegExp("^#(" + et + ")"),
            CLASS: RegExp("^\\.(" + et + ")"),
            NAME: RegExp("^\\[name=['\"]?(" + et + ")['\"]?\\]"),
            TAG: RegExp("^(" + et.replace("w", "w*") + ")"),
            ATTR: RegExp("^" + rt),
            PSEUDO: RegExp("^" + ot),
            CHILD: RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + tt + "*(even|odd|(([+-]|)(\\d*)n|)" + tt + "*(?:([+-]|)" + tt + "*(\\d+)|))" + tt + "*\\)|)", "i"),
            needsContext: RegExp("^" + tt + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + tt + "*((?:-\\d)?\\d*)" + tt + "*\\)|)(?=[^-]|$)", "i")
        }, dt = /[\x20\t\r\n\f]*[+~]/, pt = /^[^{]+\{\s*\[native code/, mt = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, gt = /^(?:input|select|textarea|button)$/i, vt = /^h\d$/i, yt = /'|\\/g, bt = /\=[\x20\t\r\n\f]*([^'"\]]*)[\x20\t\r\n\f]*\]/g, xt = /\\([\da-fA-F]{1,6}[\x20\t\r\n\f]?|.)/g, wt = function(t, e) {
            var n = "0x" + e - 65536;
            return n !== n ? e : 0 > n ? String.fromCharCode(n + 65536) : String.fromCharCode(55296 | n >> 10, 56320 | 1023 & n);
        };
        try {
            Q.call(z.documentElement.childNodes, 0)[0].nodeType;
        } catch (_t) {
            Q = function(t) {
                for (var e, n = []; e = this[t++]; ) n.push(e);
                return n;
            };
        }
        T = s.isXML = function(t) {
            var e = t && (t.ownerDocument || t).documentElement;
            return e ? "HTML" !== e.nodeName : !1;
        }, F = s.setDocument = function(t) {
            var i = t ? t.ownerDocument || t : z;
            return i !== $ && 9 === i.nodeType && i.documentElement ? ($ = i, j = i.documentElement,
                M = T(i), I.tagNameNoComments = o(function(t) {
                return t.appendChild(i.createComment("")), !t.getElementsByTagName("*").length;
            }), I.attributes = o(function(t) {
                t.innerHTML = "<select></select>";
                var e = typeof t.lastChild.getAttribute("multiple");
                return "boolean" !== e && "string" !== e;
            }), I.getByClassName = o(function(t) {
                return t.innerHTML = "<div class='hidden e'></div><div class='hidden'></div>", t.getElementsByClassName && t.getElementsByClassName("e").length ? (t.lastChild.className = "e",
                2 === t.getElementsByClassName("e").length) : !1;
            }), I.getByName = o(function(t) {
                t.id = R + 0, t.innerHTML = "<a name='" + R + "'></a><div name='" + R + "'></div>",
                    j.insertBefore(t, j.firstChild);
                var e = i.getElementsByName && i.getElementsByName(R).length === 2 + i.getElementsByName(R + 0).length;
                return I.getIdNotName = !i.getElementById(R), j.removeChild(t), e;
            }), C.attrHandle = o(function(t) {
                return t.innerHTML = "<a href='#'></a>", t.firstChild && typeof t.firstChild.getAttribute !== X && "#" === t.firstChild.getAttribute("href");
            }) ? {} : {
                href: function(t) {
                    return t.getAttribute("href", 2);
                },
                type: function(t) {
                    return t.getAttribute("type");
                }
            }, I.getIdNotName ? (C.find.ID = function(t, e) {
                if (typeof e.getElementById !== X && !M) {
                    var n = e.getElementById(t);
                    return n && n.parentNode ? [ n ] : [];
                }
            }, C.filter.ID = function(t) {
                var e = t.replace(xt, wt);
                return function(t) {
                    return t.getAttribute("id") === e;
                };
            }) : (C.find.ID = function(t, n) {
                if (typeof n.getElementById !== X && !M) {
                    var i = n.getElementById(t);
                    return i ? i.id === t || typeof i.getAttributeNode !== X && i.getAttributeNode("id").value === t ? [ i ] : e : [];
                }
            }, C.filter.ID = function(t) {
                var e = t.replace(xt, wt);
                return function(t) {
                    var n = typeof t.getAttributeNode !== X && t.getAttributeNode("id");
                    return n && n.value === e;
                };
            }), C.find.TAG = I.tagNameNoComments ? function(t, n) {
                return typeof n.getElementsByTagName !== X ? n.getElementsByTagName(t) : e;
            } : function(t, e) {
                var n, i = [], r = 0, o = e.getElementsByTagName(t);
                if ("*" === t) {
                    for (;n = o[r++]; ) 1 === n.nodeType && i.push(n);
                    return i;
                }
                return o;
            }, C.find.NAME = I.getByName && function(t, n) {
                    return typeof n.getElementsByName !== X ? n.getElementsByName(name) : e;
                }, C.find.CLASS = I.getByClassName && function(t, n) {
                    return typeof n.getElementsByClassName === X || M ? e : n.getElementsByClassName(t);
                }, L = [], D = [ ":focus" ], (I.qsa = n(i.querySelectorAll)) && (o(function(t) {
                t.innerHTML = "<select><option selected=''></option></select>", t.querySelectorAll("[selected]").length || D.push("\\[" + tt + "*(?:checked|disabled|ismap|multiple|readonly|selected|value)"),
                t.querySelectorAll(":checked").length || D.push(":checked");
            }), o(function(t) {
                t.innerHTML = "<input type='hidden' i=''/>", t.querySelectorAll("[i^='']").length && D.push("[*^$]=" + tt + "*(?:\"\"|'')"),
                t.querySelectorAll(":enabled").length || D.push(":enabled", ":disabled"), t.querySelectorAll("*,:x"),
                    D.push(",.*:");
            })), (I.matchesSelector = n(O = j.matchesSelector || j.mozMatchesSelector || j.webkitMatchesSelector || j.oMatchesSelector || j.msMatchesSelector)) && o(function(t) {
                I.disconnectedMatch = O.call(t, "div"), O.call(t, "[s!='']:x"), L.push("!=", ot);
            }), D = RegExp(D.join("|")), L = RegExp(L.join("|")), P = n(j.contains) || j.compareDocumentPosition ? function(t, e) {
                var n = 9 === t.nodeType ? t.documentElement : t, i = e && e.parentNode;
                return t === i || !(!i || 1 !== i.nodeType || !(n.contains ? n.contains(i) : t.compareDocumentPosition && 16 & t.compareDocumentPosition(i)));
            } : function(t, e) {
                if (e) for (;e = e.parentNode; ) if (e === t) return !0;
                return !1;
            }, B = j.compareDocumentPosition ? function(t, e) {
                var n;
                return t === e ? (N = !0, 0) : (n = e.compareDocumentPosition && t.compareDocumentPosition && t.compareDocumentPosition(e)) ? 1 & n || t.parentNode && 11 === t.parentNode.nodeType ? t === i || P(z, t) ? -1 : e === i || P(z, e) ? 1 : 0 : 4 & n ? -1 : 1 : t.compareDocumentPosition ? -1 : 1;
            } : function(t, e) {
                var n, r = 0, o = t.parentNode, s = e.parentNode, u = [ t ], c = [ e ];
                if (t === e) return N = !0, 0;
                if (!o || !s) return t === i ? -1 : e === i ? 1 : o ? -1 : s ? 1 : 0;
                if (o === s) return a(t, e);
                for (n = t; n = n.parentNode; ) u.unshift(n);
                for (n = e; n = n.parentNode; ) c.unshift(n);
                for (;u[r] === c[r]; ) r++;
                return r ? a(u[r], c[r]) : u[r] === z ? -1 : c[r] === z ? 1 : 0;
            }, N = !1, [ 0, 0 ].sort(B), I.detectDuplicates = N, $) : $;
        }, s.matches = function(t, e) {
            return s(t, null, null, e);
        }, s.matchesSelector = function(t, e) {
            if ((t.ownerDocument || t) !== $ && F(t), e = e.replace(bt, "='$1']"), !(!I.matchesSelector || M || L && L.test(e) || D.test(e))) try {
                var n = O.call(t, e);
                if (n || I.disconnectedMatch || t.document && 11 !== t.document.nodeType) return n;
            } catch (i) {}
            return s(e, $, null, [ t ]).length > 0;
        }, s.contains = function(t, e) {
            return (t.ownerDocument || t) !== $ && F(t), P(t, e);
        }, s.attr = function(t, e) {
            var n;
            return (t.ownerDocument || t) !== $ && F(t), M || (e = e.toLowerCase()), (n = C.attrHandle[e]) ? n(t) : M || I.attributes ? t.getAttribute(e) : ((n = t.getAttributeNode(e)) || t.getAttribute(e)) && t[e] === !0 ? e : n && n.specified ? n.value : null;
        }, s.error = function(t) {
            throw Error("Syntax error, unrecognized expression: " + t);
        }, s.uniqueSort = function(t) {
            var e, n = [], i = 1, r = 0;
            if (N = !I.detectDuplicates, t.sort(B), N) {
                for (;e = t[i]; i++) e === t[i - 1] && (r = n.push(i));
                for (;r--; ) t.splice(n[r], 1);
            }
            return t;
        }, E = s.getText = function(t) {
            var e, n = "", i = 0, r = t.nodeType;
            if (r) {
                if (1 === r || 9 === r || 11 === r) {
                    if ("string" == typeof t.textContent) return t.textContent;
                    for (t = t.firstChild; t; t = t.nextSibling) n += E(t);
                } else if (3 === r || 4 === r) return t.nodeValue;
            } else for (;e = t[i]; i++) n += E(e);
            return n;
        }, C = s.selectors = {
            cacheLength: 50,
            createPseudo: r,
            match: ft,
            find: {},
            relative: {
                ">": {
                    dir: "parentNode",
                    first: !0
                },
                " ": {
                    dir: "parentNode"
                },
                "+": {
                    dir: "previousSibling",
                    first: !0
                },
                "~": {
                    dir: "previousSibling"
                }
            },
            preFilter: {
                ATTR: function(t) {
                    return t[1] = t[1].replace(xt, wt), t[3] = (t[4] || t[5] || "").replace(xt, wt),
                    "~=" === t[2] && (t[3] = " " + t[3] + " "), t.slice(0, 4);
                },
                CHILD: function(t) {
                    return t[1] = t[1].toLowerCase(), "nth" === t[1].slice(0, 3) ? (t[3] || s.error(t[0]),
                        t[4] = +(t[4] ? t[5] + (t[6] || 1) : 2 * ("even" === t[3] || "odd" === t[3])), t[5] = +(t[7] + t[8] || "odd" === t[3])) : t[3] && s.error(t[0]),
                        t;
                },
                PSEUDO: function(t) {
                    var e, n = !t[5] && t[2];
                    return ft.CHILD.test(t[0]) ? null : (t[4] ? t[2] = t[4] : n && lt.test(n) && (e = h(n, !0)) && (e = n.indexOf(")", n.length - e) - n.length) && (t[0] = t[0].slice(0, e),
                        t[2] = n.slice(0, e)), t.slice(0, 3));
                }
            },
            filter: {
                TAG: function(t) {
                    return "*" === t ? function() {
                        return !0;
                    } : (t = t.replace(xt, wt).toLowerCase(), function(e) {
                        return e.nodeName && e.nodeName.toLowerCase() === t;
                    });
                },
                CLASS: function(t) {
                    var e = U[t + " "];
                    return e || (e = RegExp("(^|" + tt + ")" + t + "(" + tt + "|$)")) && U(t, function(t) {
                            return e.test(t.className || typeof t.getAttribute !== X && t.getAttribute("class") || "");
                        });
                },
                ATTR: function(t, e, n) {
                    return function(i) {
                        var r = s.attr(i, t);
                        return null == r ? "!=" === e : e ? (r += "", "=" === e ? r === n : "!=" === e ? r !== n : "^=" === e ? n && 0 === r.indexOf(n) : "*=" === e ? n && r.indexOf(n) > -1 : "$=" === e ? n && r.slice(-n.length) === n : "~=" === e ? (" " + r + " ").indexOf(n) > -1 : "|=" === e ? r === n || r.slice(0, n.length + 1) === n + "-" : !1) : !0;
                    };
                },
                CHILD: function(t, e, n, i, r) {
                    var o = "nth" !== t.slice(0, 3), s = "last" !== t.slice(-4), a = "of-type" === e;
                    return 1 === i && 0 === r ? function(t) {
                        return !!t.parentNode;
                    } : function(e, n, u) {
                        var c, l, h, f, d, p, m = o !== s ? "nextSibling" : "previousSibling", g = e.parentNode, v = a && e.nodeName.toLowerCase(), y = !u && !a;
                        if (g) {
                            if (o) {
                                for (;m; ) {
                                    for (h = e; h = h[m]; ) if (a ? h.nodeName.toLowerCase() === v : 1 === h.nodeType) return !1;
                                    p = m = "only" === t && !p && "nextSibling";
                                }
                                return !0;
                            }
                            if (p = [ s ? g.firstChild : g.lastChild ], s && y) {
                                for (l = g[R] || (g[R] = {}), c = l[t] || [], d = c[0] === q && c[1], f = c[0] === q && c[2],
                                         h = d && g.childNodes[d]; h = ++d && h && h[m] || (f = d = 0) || p.pop(); ) if (1 === h.nodeType && ++f && h === e) {
                                    l[t] = [ q, d, f ];
                                    break;
                                }
                            } else if (y && (c = (e[R] || (e[R] = {}))[t]) && c[0] === q) f = c[1]; else for (;(h = ++d && h && h[m] || (f = d = 0) || p.pop()) && ((a ? h.nodeName.toLowerCase() !== v : 1 !== h.nodeType) || !++f || (y && ((h[R] || (h[R] = {}))[t] = [ q, f ]),
                            h !== e)); ) ;
                            return f -= r, f === i || 0 === f % i && f / i >= 0;
                        }
                    };
                },
                PSEUDO: function(t, e) {
                    var n, i = C.pseudos[t] || C.setFilters[t.toLowerCase()] || s.error("unsupported pseudo: " + t);
                    return i[R] ? i(e) : i.length > 1 ? (n = [ t, t, "", e ], C.setFilters.hasOwnProperty(t.toLowerCase()) ? r(function(t, n) {
                        for (var r, o = i(t, e), s = o.length; s--; ) r = K.call(t, o[s]), t[r] = !(n[r] = o[s]);
                    }) : function(t) {
                        return i(t, 0, n);
                    }) : i;
                }
            },
            pseudos: {
                not: r(function(t) {
                    var e = [], n = [], i = A(t.replace(st, "$1"));
                    return i[R] ? r(function(t, e, n, r) {
                        for (var o, s = i(t, null, r, []), a = t.length; a--; ) (o = s[a]) && (t[a] = !(e[a] = o));
                    }) : function(t, r, o) {
                        return e[0] = t, i(e, null, o, n), !n.pop();
                    };
                }),
                has: r(function(t) {
                    return function(e) {
                        return s(t, e).length > 0;
                    };
                }),
                contains: r(function(t) {
                    return function(e) {
                        return (e.textContent || e.innerText || E(e)).indexOf(t) > -1;
                    };
                }),
                lang: r(function(t) {
                    return ht.test(t || "") || s.error("unsupported lang: " + t), t = t.replace(xt, wt).toLowerCase(),
                        function(e) {
                            var n;
                            do if (n = M ? e.getAttribute("xml:lang") || e.getAttribute("lang") : e.lang) return n = n.toLowerCase(),
                            n === t || 0 === n.indexOf(t + "-"); while ((e = e.parentNode) && 1 === e.nodeType);
                            return !1;
                        };
                }),
                target: function(e) {
                    var n = t.location && t.location.hash;
                    return n && n.slice(1) === e.id;
                },
                root: function(t) {
                    return t === j;
                },
                focus: function(t) {
                    return t === $.activeElement && (!$.hasFocus || $.hasFocus()) && !!(t.type || t.href || ~t.tabIndex);
                },
                enabled: function(t) {
                    return t.disabled === !1;
                },
                disabled: function(t) {
                    return t.disabled === !0;
                },
                checked: function(t) {
                    var e = t.nodeName.toLowerCase();
                    return "input" === e && !!t.checked || "option" === e && !!t.selected;
                },
                selected: function(t) {
                    return t.parentNode && t.parentNode.selectedIndex, t.selected === !0;
                },
                empty: function(t) {
                    for (t = t.firstChild; t; t = t.nextSibling) if (t.nodeName > "@" || 3 === t.nodeType || 4 === t.nodeType) return !1;
                    return !0;
                },
                parent: function(t) {
                    return !C.pseudos.empty(t);
                },
                header: function(t) {
                    return vt.test(t.nodeName);
                },
                input: function(t) {
                    return gt.test(t.nodeName);
                },
                button: function(t) {
                    var e = t.nodeName.toLowerCase();
                    return "input" === e && "button" === t.type || "button" === e;
                },
                text: function(t) {
                    var e;
                    return "input" === t.nodeName.toLowerCase() && "text" === t.type && (null == (e = t.getAttribute("type")) || e.toLowerCase() === t.type);
                },
                first: l(function() {
                    return [ 0 ];
                }),
                last: l(function(t, e) {
                    return [ e - 1 ];
                }),
                eq: l(function(t, e, n) {
                    return [ 0 > n ? n + e : n ];
                }),
                even: l(function(t, e) {
                    for (var n = 0; e > n; n += 2) t.push(n);
                    return t;
                }),
                odd: l(function(t, e) {
                    for (var n = 1; e > n; n += 2) t.push(n);
                    return t;
                }),
                lt: l(function(t, e, n) {
                    for (var i = 0 > n ? n + e : n; --i >= 0; ) t.push(i);
                    return t;
                }),
                gt: l(function(t, e, n) {
                    for (var i = 0 > n ? n + e : n; e > ++i; ) t.push(i);
                    return t;
                })
            }
        };
        for (_ in {
            radio: !0,
            checkbox: !0,
            file: !0,
            password: !0,
            image: !0
        }) C.pseudos[_] = u(_);
        for (_ in {
            submit: !0,
            reset: !0
        }) C.pseudos[_] = c(_);
        A = s.compile = function(t, e) {
            var n, i = [], r = [], o = W[t + " "];
            if (!o) {
                for (e || (e = h(t)), n = e.length; n--; ) o = v(e[n]), o[R] ? i.push(o) : r.push(o);
                o = W(t, y(r, i));
            }
            return o;
        }, C.pseudos.nth = C.pseudos.eq, C.filters = w.prototype = C.pseudos, C.setFilters = new w(),
            F(), s.attr = ut.attr, ut.find = s, ut.expr = s.selectors, ut.expr[":"] = ut.expr.pseudos,
            ut.unique = s.uniqueSort, ut.text = s.getText, ut.isXMLDoc = s.isXML, ut.contains = s.contains;
    }(t);
    var It = /Until$/, qt = /^(?:parents|prev(?:Until|All))/, Ht = /^.[^:#\[\.,]*$/, Ut = ut.expr.match.needsContext, Vt = {
        children: !0,
        contents: !0,
        next: !0,
        prev: !0
    };
    ut.fn.extend({
        find: function(t) {
            var e, n, i, r = this.length;
            if ("string" != typeof t) return i = this, this.pushStack(ut(t).filter(function() {
                for (e = 0; r > e; e++) if (ut.contains(i[e], this)) return !0;
            }));
            for (n = [], e = 0; r > e; e++) ut.find(t, this[e], n);
            return n = this.pushStack(r > 1 ? ut.unique(n) : n), n.selector = (this.selector ? this.selector + " " : "") + t,
                n;
        },
        has: function(t) {
            var e, n = ut(t, this), i = n.length;
            return this.filter(function() {
                for (e = 0; i > e; e++) if (ut.contains(this, n[e])) return !0;
            });
        },
        not: function(t) {
            return this.pushStack(h(this, t, !1));
        },
        filter: function(t) {
            return this.pushStack(h(this, t, !0));
        },
        is: function(t) {
            return !!t && ("string" == typeof t ? Ut.test(t) ? ut(t, this.context).index(this[0]) >= 0 : ut.filter(t, this).length > 0 : this.filter(t).length > 0);
        },
        closest: function(t, e) {
            for (var n, i = 0, r = this.length, o = [], s = Ut.test(t) || "string" != typeof t ? ut(t, e || this.context) : 0; r > i; i++) for (n = this[i]; n && n.ownerDocument && n !== e && 11 !== n.nodeType; ) {
                if (s ? s.index(n) > -1 : ut.find.matchesSelector(n, t)) {
                    o.push(n);
                    break;
                }
                n = n.parentNode;
            }
            return this.pushStack(o.length > 1 ? ut.unique(o) : o);
        },
        index: function(t) {
            return t ? "string" == typeof t ? ut.inArray(this[0], ut(t)) : ut.inArray(t.jquery ? t[0] : t, this) : this[0] && this[0].parentNode ? this.first().prevAll().length : -1;
        },
        add: function(t, e) {
            var n = "string" == typeof t ? ut(t, e) : ut.makeArray(t && t.nodeType ? [ t ] : t), i = ut.merge(this.get(), n);
            return this.pushStack(ut.unique(i));
        },
        addBack: function(t) {
            return this.add(null == t ? this.prevObject : this.prevObject.filter(t));
        }
    }), ut.fn.andSelf = ut.fn.addBack, ut.each({
        parent: function(t) {
            var e = t.parentNode;
            return e && 11 !== e.nodeType ? e : null;
        },
        parents: function(t) {
            return ut.dir(t, "parentNode");
        },
        parentsUntil: function(t, e, n) {
            return ut.dir(t, "parentNode", n);
        },
        next: function(t) {
            return l(t, "nextSibling");
        },
        prev: function(t) {
            return l(t, "previousSibling");
        },
        nextAll: function(t) {
            return ut.dir(t, "nextSibling");
        },
        prevAll: function(t) {
            return ut.dir(t, "previousSibling");
        },
        nextUntil: function(t, e, n) {
            return ut.dir(t, "nextSibling", n);
        },
        prevUntil: function(t, e, n) {
            return ut.dir(t, "previousSibling", n);
        },
        siblings: function(t) {
            return ut.sibling((t.parentNode || {}).firstChild, t);
        },
        children: function(t) {
            return ut.sibling(t.firstChild);
        },
        contents: function(t) {
            return ut.nodeName(t, "iframe") ? t.contentDocument || t.contentWindow.document : ut.merge([], t.childNodes);
        }
    }, function(t, e) {
        ut.fn[t] = function(n, i) {
            var r = ut.map(this, e, n);
            return It.test(t) || (i = n), i && "string" == typeof i && (r = ut.filter(i, r)),
                r = this.length > 1 && !Vt[t] ? ut.unique(r) : r, this.length > 1 && qt.test(t) && (r = r.reverse()),
                this.pushStack(r);
        };
    }), ut.extend({
        filter: function(t, e, n) {
            return n && (t = ":not(" + t + ")"), 1 === e.length ? ut.find.matchesSelector(e[0], t) ? [ e[0] ] : [] : ut.find.matches(t, e);
        },
        dir: function(t, n, i) {
            for (var r = [], o = t[n]; o && 9 !== o.nodeType && (i === e || 1 !== o.nodeType || !ut(o).is(i)); ) 1 === o.nodeType && r.push(o),
                o = o[n];
            return r;
        },
        sibling: function(t, e) {
            for (var n = []; t; t = t.nextSibling) 1 === t.nodeType && t !== e && n.push(t);
            return n;
        }
    });
    var Wt = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video", Xt = / jQuery\d+="(?:null|\d+)"/g, Gt = RegExp("<(?:" + Wt + ")[\\s/>]", "i"), Yt = /^\s+/, Jt = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi, Zt = /<([\w:]+)/, Qt = /<tbody/i, Kt = /<|&#?\w+;/, te = /<(?:script|style|link)/i, ee = /^(?:checkbox|radio)$/i, ne = /checked\s*(?:[^=]|=\s*.checked.)/i, ie = /^$|\/(?:java|ecma)script/i, re = /^true\/(.*)/, oe = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g, se = {
        option: [ 1, "<select multiple='multiple'>", "</select>" ],
        legend: [ 1, "<fieldset>", "</fieldset>" ],
        area: [ 1, "<map>", "</map>" ],
        param: [ 1, "<object>", "</object>" ],
        thead: [ 1, "<table>", "</table>" ],
        tr: [ 2, "<table><tbody>", "</tbody></table>" ],
        col: [ 2, "<table><tbody></tbody><colgroup>", "</colgroup></table>" ],
        td: [ 3, "<table><tbody><tr>", "</tr></tbody></table>" ],
        _default: ut.support.htmlSerialize ? [ 0, "", "" ] : [ 1, "X<div>", "</div>" ]
    }, ae = f(G), ue = ae.appendChild(G.createElement("div"));
    se.optgroup = se.option, se.tbody = se.tfoot = se.colgroup = se.caption = se.thead,
        se.th = se.td, ut.fn.extend({
        text: function(t) {
            return ut.access(this, function(t) {
                return t === e ? ut.text(this) : this.empty().append((this[0] && this[0].ownerDocument || G).createTextNode(t));
            }, null, t, arguments.length);
        },
        wrapAll: function(t) {
            if (ut.isFunction(t)) return this.each(function(e) {
                ut(this).wrapAll(t.call(this, e));
            });
            if (this[0]) {
                var e = ut(t, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && e.insertBefore(this[0]), e.map(function() {
                    for (var t = this; t.firstChild && 1 === t.firstChild.nodeType; ) t = t.firstChild;
                    return t;
                }).append(this);
            }
            return this;
        },
        wrapInner: function(t) {
            return this.each(ut.isFunction(t) ? function(e) {
                ut(this).wrapInner(t.call(this, e));
            } : function() {
                var e = ut(this), n = e.contents();
                n.length ? n.wrapAll(t) : e.append(t);
            });
        },
        wrap: function(t) {
            var e = ut.isFunction(t);
            return this.each(function(n) {
                ut(this).wrapAll(e ? t.call(this, n) : t);
            });
        },
        unwrap: function() {
            return this.parent().each(function() {
                ut.nodeName(this, "body") || ut(this).replaceWith(this.childNodes);
            }).end();
        },
        append: function() {
            return this.domManip(arguments, !0, function(t) {
                (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) && this.appendChild(t);
            });
        },
        prepend: function() {
            return this.domManip(arguments, !0, function(t) {
                (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) && this.insertBefore(t, this.firstChild);
            });
        },
        before: function() {
            return this.domManip(arguments, !1, function(t) {
                this.parentNode && this.parentNode.insertBefore(t, this);
            });
        },
        after: function() {
            return this.domManip(arguments, !1, function(t) {
                this.parentNode && this.parentNode.insertBefore(t, this.nextSibling);
            });
        },
        remove: function(t, e) {
            for (var n, i = 0; null != (n = this[i]); i++) (!t || ut.filter(t, [ n ]).length > 0) && (e || 1 !== n.nodeType || ut.cleanData(b(n)),
            n.parentNode && (e && ut.contains(n.ownerDocument, n) && g(b(n, "script")), n.parentNode.removeChild(n)));
            return this;
        },
        empty: function() {
            for (var t, e = 0; null != (t = this[e]); e++) {
                for (1 === t.nodeType && ut.cleanData(b(t, !1)); t.firstChild; ) t.removeChild(t.firstChild);
                t.options && ut.nodeName(t, "select") && (t.options.length = 0);
            }
            return this;
        },
        clone: function(t, e) {
            return t = null == t ? !1 : t, e = null == e ? t : e, this.map(function() {
                return ut.clone(this, t, e);
            });
        },
        html: function(t) {
            return ut.access(this, function(t) {
                var n = this[0] || {}, i = 0, r = this.length;
                if (t === e) return 1 === n.nodeType ? n.innerHTML.replace(Xt, "") : e;
                if (!("string" != typeof t || te.test(t) || !ut.support.htmlSerialize && Gt.test(t) || !ut.support.leadingWhitespace && Yt.test(t) || se[(Zt.exec(t) || [ "", "" ])[1].toLowerCase()])) {
                    t = t.replace(Jt, "<$1></$2>");
                    try {
                        for (;r > i; i++) n = this[i] || {}, 1 === n.nodeType && (ut.cleanData(b(n, !1)),
                            n.innerHTML = t);
                        n = 0;
                    } catch (o) {}
                }
                n && this.empty().append(t);
            }, null, t, arguments.length);
        },
        replaceWith: function(t) {
            var e = ut.isFunction(t);
            return e || "string" == typeof t || (t = ut(t).not(this).detach()), this.domManip([ t ], !0, function(t) {
                var e = this.nextSibling, n = this.parentNode;
                n && (ut(this).remove(), n.insertBefore(t, e));
            });
        },
        detach: function(t) {
            return this.remove(t, !0);
        },
        domManip: function(t, n, i) {
            t = et.apply([], t);
            var r, o, s, a, u, c, l = 0, h = this.length, f = this, g = h - 1, v = t[0], y = ut.isFunction(v);
            if (y || !(1 >= h || "string" != typeof v || ut.support.checkClone) && ne.test(v)) return this.each(function(r) {
                var o = f.eq(r);
                y && (t[0] = v.call(this, r, n ? o.html() : e)), o.domManip(t, n, i);
            });
            if (h && (c = ut.buildFragment(t, this[0].ownerDocument, !1, this), r = c.firstChild,
                1 === c.childNodes.length && (c = r), r)) {
                for (n = n && ut.nodeName(r, "tr"), a = ut.map(b(c, "script"), p), s = a.length; h > l; l++) o = c,
                l !== g && (o = ut.clone(o, !0, !0), s && ut.merge(a, b(o, "script"))), i.call(n && ut.nodeName(this[l], "table") ? d(this[l], "tbody") : this[l], o, l);
                if (s) for (u = a[a.length - 1].ownerDocument, ut.map(a, m), l = 0; s > l; l++) o = a[l],
                ie.test(o.type || "") && !ut._data(o, "globalEval") && ut.contains(u, o) && (o.src ? ut.ajax({
                    url: o.src,
                    type: "GET",
                    dataType: "script",
                    async: !1,
                    global: !1,
                    "throws": !0
                }) : ut.globalEval((o.text || o.textContent || o.innerHTML || "").replace(oe, "")));
                c = r = null;
            }
            return this;
        }
    }), ut.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    }, function(t, e) {
        ut.fn[t] = function(t) {
            for (var n, i = 0, r = [], o = ut(t), s = o.length - 1; s >= i; i++) n = i === s ? this : this.clone(!0),
                ut(o[i])[e](n), nt.apply(r, n.get());
            return this.pushStack(r);
        };
    }), ut.extend({
        clone: function(t, e, n) {
            var i, r, o, s, a, u = ut.contains(t.ownerDocument, t);
            if (ut.support.html5Clone || ut.isXMLDoc(t) || !Gt.test("<" + t.nodeName + ">") ? o = t.cloneNode(!0) : (ue.innerHTML = t.outerHTML,
                    ue.removeChild(o = ue.firstChild)), !(ut.support.noCloneEvent && ut.support.noCloneChecked || 1 !== t.nodeType && 11 !== t.nodeType || ut.isXMLDoc(t))) for (i = b(o),
                                                                                                                                                                                     a = b(t), s = 0; null != (r = a[s]); ++s) i[s] && y(r, i[s]);
            if (e) if (n) for (a = a || b(t), i = i || b(o), s = 0; null != (r = a[s]); s++) v(r, i[s]); else v(t, o);
            return i = b(o, "script"), i.length > 0 && g(i, !u && b(t, "script")), i = a = r = null,
                o;
        },
        buildFragment: function(t, e, n, i) {
            for (var r, o, s, a, u, c, l, h = t.length, d = f(e), p = [], m = 0; h > m; m++) if (o = t[m],
                o || 0 === o) if ("object" === ut.type(o)) ut.merge(p, o.nodeType ? [ o ] : o); else if (Kt.test(o)) {
                for (a = a || d.appendChild(e.createElement("div")), u = (Zt.exec(o) || [ "", "" ])[1].toLowerCase(),
                         l = se[u] || se._default, a.innerHTML = l[1] + o.replace(Jt, "<$1></$2>") + l[2],
                         r = l[0]; r--; ) a = a.lastChild;
                if (!ut.support.leadingWhitespace && Yt.test(o) && p.push(e.createTextNode(Yt.exec(o)[0])),
                        !ut.support.tbody) for (o = "table" !== u || Qt.test(o) ? "<table>" !== l[1] || Qt.test(o) ? 0 : a : a.firstChild,
                                                    r = o && o.childNodes.length; r--; ) ut.nodeName(c = o.childNodes[r], "tbody") && !c.childNodes.length && o.removeChild(c);
                for (ut.merge(p, a.childNodes), a.textContent = ""; a.firstChild; ) a.removeChild(a.firstChild);
                a = d.lastChild;
            } else p.push(e.createTextNode(o));
            for (a && d.removeChild(a), ut.support.appendChecked || ut.grep(b(p, "input"), x),
                     m = 0; o = p[m++]; ) if ((!i || -1 === ut.inArray(o, i)) && (s = ut.contains(o.ownerDocument, o),
                    a = b(d.appendChild(o), "script"), s && g(a), n)) for (r = 0; o = a[r++]; ) ie.test(o.type || "") && n.push(o);
            return a = null, d;
        },
        cleanData: function(t, e) {
            for (var n, i, r, o, s = 0, a = ut.expando, u = ut.cache, c = ut.support.deleteExpando, l = ut.event.special; null != (n = t[s]); s++) if ((e || ut.acceptData(n)) && (r = n[a],
                    o = r && u[r])) {
                if (o.events) for (i in o.events) l[i] ? ut.event.remove(n, i) : ut.removeEvent(n, i, o.handle);
                u[r] && (delete u[r], c ? delete n[a] : typeof n.removeAttribute !== X ? n.removeAttribute(a) : n[a] = null,
                    K.push(r));
            }
        }
    });
    var ce, le, he, fe = /alpha\([^)]*\)/i, de = /opacity\s*=\s*([^)]*)/, pe = /^(top|right|bottom|left)$/, me = /^(none|table(?!-c[ea]).+)/, ge = /^margin/, ve = RegExp("^(" + ct + ")(.*)$", "i"), ye = RegExp("^(" + ct + ")(?!px)[a-z%]+$", "i"), be = RegExp("^([+-])=(" + ct + ")", "i"), xe = {
        BODY: "block"
    }, we = {
        position: "absolute",
        visibility: "hidden",
        display: "block"
    }, _e = {
        letterSpacing: 0,
        fontWeight: 400
    }, ke = [ "Top", "Right", "Bottom", "Left" ], Ce = [ "Webkit", "O", "Moz", "ms" ];
    ut.fn.extend({
        css: function(t, n) {
            return ut.access(this, function(t, n, i) {
                var r, o, s = {}, a = 0;
                if (ut.isArray(n)) {
                    for (o = le(t), r = n.length; r > a; a++) s[n[a]] = ut.css(t, n[a], !1, o);
                    return s;
                }
                return i !== e ? ut.style(t, n, i) : ut.css(t, n);
            }, t, n, arguments.length > 1);
        },
        show: function() {
            return k(this, !0);
        },
        hide: function() {
            return k(this);
        },
        toggle: function(t) {
            var e = "boolean" == typeof t;
            return this.each(function() {
                (e ? t : _(this)) ? ut(this).show() : ut(this).hide();
            });
        }
    }), ut.extend({
        cssHooks: {
            opacity: {
                get: function(t, e) {
                    if (e) {
                        var n = he(t, "opacity");
                        return "" === n ? "1" : n;
                    }
                }
            }
        },
        cssNumber: {
            columnCount: !0,
            fillOpacity: !0,
            fontWeight: !0,
            lineHeight: !0,
            opacity: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {
            "float": ut.support.cssFloat ? "cssFloat" : "styleFloat"
        },
        style: function(t, n, i, r) {
            if (t && 3 !== t.nodeType && 8 !== t.nodeType && t.style) {
                var o, s, a, u = ut.camelCase(n), c = t.style;
                if (n = ut.cssProps[u] || (ut.cssProps[u] = w(c, u)), a = ut.cssHooks[n] || ut.cssHooks[u],
                    i === e) return a && "get" in a && (o = a.get(t, !1, r)) !== e ? o : c[n];
                if (s = typeof i, "string" === s && (o = be.exec(i)) && (i = (o[1] + 1) * o[2] + parseFloat(ut.css(t, n)),
                        s = "number"), !(null == i || "number" === s && isNaN(i) || ("number" !== s || ut.cssNumber[u] || (i += "px"),
                    ut.support.clearCloneStyle || "" !== i || 0 !== n.indexOf("background") || (c[n] = "inherit"),
                    a && "set" in a && (i = a.set(t, i, r)) === e))) try {
                    c[n] = i;
                } catch (l) {}
            }
        },
        css: function(t, n, i, r) {
            var o, s, a, u = ut.camelCase(n);
            return n = ut.cssProps[u] || (ut.cssProps[u] = w(t.style, u)), a = ut.cssHooks[n] || ut.cssHooks[u],
            a && "get" in a && (s = a.get(t, !0, i)), s === e && (s = he(t, n, r)), "normal" === s && n in _e && (s = _e[n]),
                "" === i || i ? (o = parseFloat(s), i === !0 || ut.isNumeric(o) ? o || 0 : s) : s;
        },
        swap: function(t, e, n, i) {
            var r, o, s = {};
            for (o in e) s[o] = t.style[o], t.style[o] = e[o];
            r = n.apply(t, i || []);
            for (o in e) t.style[o] = s[o];
            return r;
        }
    }), t.getComputedStyle ? (le = function(e) {
        return t.getComputedStyle(e, null);
    }, he = function(t, n, i) {
        var r, o, s, a = i || le(t), u = a ? a.getPropertyValue(n) || a[n] : e, c = t.style;
        return a && ("" !== u || ut.contains(t.ownerDocument, t) || (u = ut.style(t, n)),
        ye.test(u) && ge.test(n) && (r = c.width, o = c.minWidth, s = c.maxWidth, c.minWidth = c.maxWidth = c.width = u,
            u = a.width, c.width = r, c.minWidth = o, c.maxWidth = s)), u;
    }) : G.documentElement.currentStyle && (le = function(t) {
        return t.currentStyle;
    }, he = function(t, n, i) {
        var r, o, s, a = i || le(t), u = a ? a[n] : e, c = t.style;
        return null == u && c && c[n] && (u = c[n]), ye.test(u) && !pe.test(n) && (r = c.left,
            o = t.runtimeStyle, s = o && o.left, s && (o.left = t.currentStyle.left), c.left = "fontSize" === n ? "1em" : u,
            u = c.pixelLeft + "px", c.left = r, s && (o.left = s)), "" === u ? "auto" : u;
    }), ut.each([ "height", "width" ], function(t, n) {
        ut.cssHooks[n] = {
            get: function(t, i, r) {
                return i ? 0 === t.offsetWidth && me.test(ut.css(t, "display")) ? ut.swap(t, we, function() {
                    return T(t, n, r);
                }) : T(t, n, r) : e;
            },
            set: function(t, e, i) {
                var r = i && le(t);
                return C(t, e, i ? E(t, n, i, ut.support.boxSizing && "border-box" === ut.css(t, "boxSizing", !1, r), r) : 0);
            }
        };
    }), ut.support.opacity || (ut.cssHooks.opacity = {
        get: function(t, e) {
            return de.test((e && t.currentStyle ? t.currentStyle.filter : t.style.filter) || "") ? .01 * parseFloat(RegExp.$1) + "" : e ? "1" : "";
        },
        set: function(t, e) {
            var n = t.style, i = t.currentStyle, r = ut.isNumeric(e) ? "alpha(opacity=" + 100 * e + ")" : "", o = i && i.filter || n.filter || "";
            n.zoom = 1, (e >= 1 || "" === e) && "" === ut.trim(o.replace(fe, "")) && n.removeAttribute && (n.removeAttribute("filter"),
            "" === e || i && !i.filter) || (n.filter = fe.test(o) ? o.replace(fe, r) : o + " " + r);
        }
    }), ut(function() {
        ut.support.reliableMarginRight || (ut.cssHooks.marginRight = {
            get: function(t, n) {
                return n ? ut.swap(t, {
                    display: "inline-block"
                }, he, [ t, "marginRight" ]) : e;
            }
        }), !ut.support.pixelPosition && ut.fn.position && ut.each([ "top", "left" ], function(t, n) {
            ut.cssHooks[n] = {
                get: function(t, i) {
                    return i ? (i = he(t, n), ye.test(i) ? ut(t).position()[n] + "px" : i) : e;
                }
            };
        });
    }), ut.expr && ut.expr.filters && (ut.expr.filters.hidden = function(t) {
        return 0 >= t.offsetWidth && 0 >= t.offsetHeight || !ut.support.reliableHiddenOffsets && "none" === (t.style && t.style.display || ut.css(t, "display"));
    }, ut.expr.filters.visible = function(t) {
        return !ut.expr.filters.hidden(t);
    }), ut.each({
        margin: "",
        padding: "",
        border: "Width"
    }, function(t, e) {
        ut.cssHooks[t + e] = {
            expand: function(n) {
                for (var i = 0, r = {}, o = "string" == typeof n ? n.split(" ") : [ n ]; 4 > i; i++) r[t + ke[i] + e] = o[i] || o[i - 2] || o[0];
                return r;
            }
        }, ge.test(t) || (ut.cssHooks[t + e].set = C);
    });
    var Ee = /%20/g, Te = /\[\]$/, Ae = /\r?\n/g, Ne = /^(?:submit|button|image|reset|file)$/i, Se = /^(?:input|select|textarea|keygen)/i;
    ut.fn.extend({
        serialize: function() {
            return ut.param(this.serializeArray());
        },
        serializeArray: function() {
            return this.map(function() {
                var t = ut.prop(this, "elements");
                return t ? ut.makeArray(t) : this;
            }).filter(function() {
                var t = this.type;
                return this.name && !ut(this).is(":disabled") && Se.test(this.nodeName) && !Ne.test(t) && (this.checked || !ee.test(t));
            }).map(function(t, e) {
                var n = ut(this).val();
                return null == n ? null : ut.isArray(n) ? ut.map(n, function(t) {
                    return {
                        name: e.name,
                        value: t.replace(Ae, "\r\n")
                    };
                }) : {
                    name: e.name,
                    value: n.replace(Ae, "\r\n")
                };
            }).get();
        }
    }), ut.param = function(t, n) {
        var i, r = [], o = function(t, e) {
            e = ut.isFunction(e) ? e() : null == e ? "" : e, r[r.length] = encodeURIComponent(t) + "=" + encodeURIComponent(e);
        };
        if (n === e && (n = ut.ajaxSettings && ut.ajaxSettings.traditional), ut.isArray(t) || t.jquery && !ut.isPlainObject(t)) ut.each(t, function() {
            o(this.name, this.value);
        }); else for (i in t) S(i, t[i], n, o);
        return r.join("&").replace(Ee, "+");
    }, ut.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function(t, e) {
        ut.fn[e] = function(t, n) {
            return arguments.length > 0 ? this.on(e, null, t, n) : this.trigger(e);
        };
    }), ut.fn.hover = function(t, e) {
        return this.mouseenter(t).mouseleave(e || t);
    };
    var Fe, $e, je = ut.now(), Me = /\?/, De = /#.*$/, Le = /([?&])_=[^&]*/, Oe = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm, Pe = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/, Be = /^(?:GET|HEAD)$/, Re = /^\/\//, ze = /^([\w.+-]+:)(?:\/\/([^\/?#:]*)(?::(\d+)|)|)/, Ie = ut.fn.load, qe = {}, He = {}, Ue = "*/".concat("*");
    try {
        $e = Y.href;
    } catch (Ve) {
        $e = G.createElement("a"), $e.href = "", $e = $e.href;
    }
    Fe = ze.exec($e.toLowerCase()) || [], ut.fn.load = function(t, n, i) {
        if ("string" != typeof t && Ie) return Ie.apply(this, arguments);
        var r, o, s, a = this, u = t.indexOf(" ");
        return u >= 0 && (r = t.slice(u, t.length), t = t.slice(0, u)), ut.isFunction(n) ? (i = n,
            n = e) : n && "object" == typeof n && (s = "POST"), a.length > 0 && ut.ajax({
            url: t,
            type: s,
            dataType: "html",
            data: n
        }).done(function(t) {
            o = arguments, a.html(r ? ut("<div>").append(ut.parseHTML(t)).find(r) : t);
        }).complete(i && function(t, e) {
                a.each(i, o || [ t.responseText, e, t ]);
            }), this;
    }, ut.each([ "ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend" ], function(t, e) {
        ut.fn[e] = function(t) {
            return this.on(e, t);
        };
    }), ut.each([ "get", "post" ], function(t, n) {
        ut[n] = function(t, i, r, o) {
            return ut.isFunction(i) && (o = o || r, r = i, i = e), ut.ajax({
                url: t,
                type: n,
                dataType: o,
                data: i,
                success: r
            });
        };
    }), ut.extend({
        active: 0,
        lastModified: {},
        etag: {},
        ajaxSettings: {
            url: $e,
            type: "GET",
            isLocal: Pe.test(Fe[1]),
            global: !0,
            processData: !0,
            async: !0,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            accepts: {
                "*": Ue,
                text: "text/plain",
                html: "text/html",
                xml: "application/xml, text/xml",
                json: "application/json, text/javascript"
            },
            contents: {
                xml: /xml/,
                html: /html/,
                json: /json/
            },
            responseFields: {
                xml: "responseXML",
                text: "responseText"
            },
            converters: {
                "* text": t.String,
                "text html": !0,
                "text json": ut.parseJSON,
                "text xml": ut.parseXML
            },
            flatOptions: {
                url: !0,
                context: !0
            }
        },
        ajaxSetup: function(t, e) {
            return e ? j(j(t, ut.ajaxSettings), e) : j(ut.ajaxSettings, t);
        },
        ajaxPrefilter: F(qe),
        ajaxTransport: F(He),
        ajax: function(t, n) {
            function i(t, n, i, r) {
                var o, h, y, b, w, k = n;
                2 !== x && (x = 2, u && clearTimeout(u), l = e, a = r || "", _.readyState = t > 0 ? 4 : 0,
                i && (b = M(f, _, i)), t >= 200 && 300 > t || 304 === t ? (f.ifModified && (w = _.getResponseHeader("Last-Modified"),
                w && (ut.lastModified[s] = w), w = _.getResponseHeader("etag"), w && (ut.etag[s] = w)),
                    204 === t ? (o = !0, k = "nocontent") : 304 === t ? (o = !0, k = "notmodified") : (o = D(f, b),
                        k = o.state, h = o.data, y = o.error, o = !y)) : (y = k, (t || !k) && (k = "error",
                0 > t && (t = 0))), _.status = t, _.statusText = (n || k) + "", o ? m.resolveWith(d, [ h, k, _ ]) : m.rejectWith(d, [ _, k, y ]),
                    _.statusCode(v), v = e, c && p.trigger(o ? "ajaxSuccess" : "ajaxError", [ _, f, o ? h : y ]),
                    g.fireWith(d, [ _, k ]), c && (p.trigger("ajaxComplete", [ _, f ]), --ut.active || ut.event.trigger("ajaxStop")));
            }
            "object" == typeof t && (n = t, t = e), n = n || {};
            var r, o, s, a, u, c, l, h, f = ut.ajaxSetup({}, n), d = f.context || f, p = f.context && (d.nodeType || d.jquery) ? ut(d) : ut.event, m = ut.Deferred(), g = ut.Callbacks("once memory"), v = f.statusCode || {}, y = {}, b = {}, x = 0, w = "canceled", _ = {
                readyState: 0,
                getResponseHeader: function(t) {
                    var e;
                    if (2 === x) {
                        if (!h) for (h = {}; e = Oe.exec(a); ) h[e[1].toLowerCase()] = e[2];
                        e = h[t.toLowerCase()];
                    }
                    return null == e ? null : e;
                },
                getAllResponseHeaders: function() {
                    return 2 === x ? a : null;
                },
                setRequestHeader: function(t, e) {
                    var n = t.toLowerCase();
                    return x || (t = b[n] = b[n] || t, y[t] = e), this;
                },
                overrideMimeType: function(t) {
                    return x || (f.mimeType = t), this;
                },
                statusCode: function(t) {
                    var e;
                    if (t) if (2 > x) for (e in t) v[e] = [ v[e], t[e] ]; else _.always(t[_.status]);
                    return this;
                },
                abort: function(t) {
                    var e = t || w;
                    return l && l.abort(e), i(0, e), this;
                }
            };
            if (m.promise(_).complete = g.add, _.success = _.done, _.error = _.fail, f.url = ((t || f.url || $e) + "").replace(De, "").replace(Re, Fe[1] + "//"),
                    f.type = n.method || n.type || f.method || f.type, f.dataTypes = ut.trim(f.dataType || "*").toLowerCase().match(lt) || [ "" ],
                null == f.crossDomain && (r = ze.exec(f.url.toLowerCase()), f.crossDomain = !(!r || r[1] === Fe[1] && r[2] === Fe[2] && (r[3] || ("http:" === r[1] ? 80 : 443)) == (Fe[3] || ("http:" === Fe[1] ? 80 : 443)))),
                f.data && f.processData && "string" != typeof f.data && (f.data = ut.param(f.data, f.traditional)),
                    $(qe, f, n, _), 2 === x) return _;
            c = f.global, c && 0 === ut.active++ && ut.event.trigger("ajaxStart"), f.type = f.type.toUpperCase(),
                f.hasContent = !Be.test(f.type), s = f.url, f.hasContent || (f.data && (s = f.url += (Me.test(s) ? "&" : "?") + f.data,
                delete f.data), f.cache === !1 && (f.url = Le.test(s) ? s.replace(Le, "$1_=" + je++) : s + (Me.test(s) ? "&" : "?") + "_=" + je++)),
            f.ifModified && (ut.lastModified[s] && _.setRequestHeader("If-Modified-Since", ut.lastModified[s]),
            ut.etag[s] && _.setRequestHeader("If-None-Match", ut.etag[s])), (f.data && f.hasContent && f.contentType !== !1 || n.contentType) && _.setRequestHeader("Content-Type", f.contentType),
                _.setRequestHeader("Accept", f.dataTypes[0] && f.accepts[f.dataTypes[0]] ? f.accepts[f.dataTypes[0]] + ("*" !== f.dataTypes[0] ? ", " + Ue + "; q=0.01" : "") : f.accepts["*"]);
            for (o in f.headers) _.setRequestHeader(o, f.headers[o]);
            if (f.beforeSend && (f.beforeSend.call(d, _, f) === !1 || 2 === x)) return _.abort();
            w = "abort";
            for (o in {
                success: 1,
                error: 1,
                complete: 1
            }) _[o](f[o]);
            if (l = $(He, f, n, _)) {
                _.readyState = 1, c && p.trigger("ajaxSend", [ _, f ]), f.async && f.timeout > 0 && (u = setTimeout(function() {
                    _.abort("timeout");
                }, f.timeout));
                try {
                    x = 1, l.send(y, i);
                } catch (k) {
                    if (!(2 > x)) throw k;
                    i(-1, k);
                }
            } else i(-1, "No Transport");
            return _;
        },
        getScript: function(t, n) {
            return ut.get(t, e, n, "script");
        },
        getJSON: function(t, e, n) {
            return ut.get(t, e, n, "json");
        }
    }), ut.ajaxSetup({
        accepts: {
            script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
        },
        contents: {
            script: /(?:java|ecma)script/
        },
        converters: {
            "text script": function(t) {
                return ut.globalEval(t), t;
            }
        }
    }), ut.ajaxPrefilter("script", function(t) {
        t.cache === e && (t.cache = !1), t.crossDomain && (t.type = "GET", t.global = !1);
    }), ut.ajaxTransport("script", function(t) {
        if (t.crossDomain) {
            var n, i = G.head || ut("head")[0] || G.documentElement;
            return {
                send: function(e, r) {
                    n = G.createElement("script"), n.async = !0, t.scriptCharset && (n.charset = t.scriptCharset),
                        n.src = t.url, n.onload = n.onreadystatechange = function(t, e) {
                        (e || !n.readyState || /loaded|complete/.test(n.readyState)) && (n.onload = n.onreadystatechange = null,
                        n.parentNode && n.parentNode.removeChild(n), n = null, e || r(200, "success"));
                    }, i.insertBefore(n, i.firstChild);
                },
                abort: function() {
                    n && n.onload(e, !0);
                }
            };
        }
    });
    var We = [], Xe = /(=)\?(?=&|$)|\?\?/;
    ut.ajaxSetup({
        jsonp: "callback",
        jsonpCallback: function() {
            var t = We.pop() || ut.expando + "_" + je++;
            return this[t] = !0, t;
        }
    }), ut.ajaxPrefilter("json jsonp", function(n, i, r) {
        var o, s, a, u = n.jsonp !== !1 && (Xe.test(n.url) ? "url" : "string" == typeof n.data && !(n.contentType || "").indexOf("application/x-www-form-urlencoded") && Xe.test(n.data) && "data");
        return u || "jsonp" === n.dataTypes[0] ? (o = n.jsonpCallback = ut.isFunction(n.jsonpCallback) ? n.jsonpCallback() : n.jsonpCallback,
            u ? n[u] = n[u].replace(Xe, "$1" + o) : n.jsonp !== !1 && (n.url += (Me.test(n.url) ? "&" : "?") + n.jsonp + "=" + o),
            n.converters["script json"] = function() {
                return a || ut.error(o + " was not called"), a[0];
            }, n.dataTypes[0] = "json", s = t[o], t[o] = function() {
            a = arguments;
        }, r.always(function() {
            t[o] = s, n[o] && (n.jsonpCallback = i.jsonpCallback, We.push(o)), a && ut.isFunction(s) && s(a[0]),
                a = s = e;
        }), "script") : e;
    });
    var Ge, Ye, Je = 0, Ze = t.ActiveXObject && function() {
            var t;
            for (t in Ge) Ge[t](e, !0);
        };
    ut.ajaxSettings.xhr = t.ActiveXObject ? function() {
        return !this.isLocal && L() || O();
    } : L, Ye = ut.ajaxSettings.xhr(), ut.support.cors = !!Ye && "withCredentials" in Ye,
        Ye = ut.support.ajax = !!Ye, Ye && ut.ajaxTransport(function(n) {
        if (!n.crossDomain || ut.support.cors) {
            var i;
            return {
                send: function(r, o) {
                    var s, a, u = n.xhr();
                    if (n.username ? u.open(n.type, n.url, n.async, n.username, n.password) : u.open(n.type, n.url, n.async),
                            n.xhrFields) for (a in n.xhrFields) u[a] = n.xhrFields[a];
                    n.mimeType && u.overrideMimeType && u.overrideMimeType(n.mimeType), n.crossDomain || r["X-Requested-With"] || (r["X-Requested-With"] = "XMLHttpRequest");
                    try {
                        for (a in r) u.setRequestHeader(a, r[a]);
                    } catch (c) {}
                    u.send(n.hasContent && n.data || null), i = function(t, r) {
                        var a, c, l, h;
                        try {
                            if (i && (r || 4 === u.readyState)) if (i = e, s && (u.onreadystatechange = ut.noop,
                                Ze && delete Ge[s]), r) 4 !== u.readyState && u.abort(); else {
                                h = {}, a = u.status, c = u.getAllResponseHeaders(), "string" == typeof u.responseText && (h.text = u.responseText);
                                try {
                                    l = u.statusText;
                                } catch (f) {
                                    l = "";
                                }
                                a || !n.isLocal || n.crossDomain ? 1223 === a && (a = 204) : a = h.text ? 200 : 404;
                            }
                        } catch (d) {
                            r || o(-1, d);
                        }
                        h && o(a, l, h, c);
                    }, n.async ? 4 === u.readyState ? setTimeout(i) : (s = ++Je, Ze && (Ge || (Ge = {},
                        ut(t).unload(Ze)), Ge[s] = i), u.onreadystatechange = i) : i();
                },
                abort: function() {
                    i && i(e, !0);
                }
            };
        }
    });
    var Qe, Ke, tn = /^(?:toggle|show|hide)$/, en = RegExp("^(?:([+-])=|)(" + ct + ")([a-z%]*)$", "i"), nn = /queueHooks$/, rn = [ I ], on = {
        "*": [ function(t, e) {
            var n, i, r = this.createTween(t, e), o = en.exec(e), s = r.cur(), a = +s || 0, u = 1, c = 20;
            if (o) {
                if (n = +o[2], i = o[3] || (ut.cssNumber[t] ? "" : "px"), "px" !== i && a) {
                    a = ut.css(r.elem, t, !0) || n || 1;
                    do u = u || ".5", a /= u, ut.style(r.elem, t, a + i); while (u !== (u = r.cur() / s) && 1 !== u && --c);
                }
                r.unit = i, r.start = a, r.end = o[1] ? a + (o[1] + 1) * n : n;
            }
            return r;
        } ]
    };
    ut.Animation = ut.extend(R, {
        tweener: function(t, e) {
            ut.isFunction(t) ? (e = t, t = [ "*" ]) : t = t.split(" ");
            for (var n, i = 0, r = t.length; r > i; i++) n = t[i], on[n] = on[n] || [], on[n].unshift(e);
        },
        prefilter: function(t, e) {
            e ? rn.unshift(t) : rn.push(t);
        }
    }), ut.Tween = q, q.prototype = {
        constructor: q,
        init: function(t, e, n, i, r, o) {
            this.elem = t, this.prop = n, this.easing = r || "swing", this.options = e, this.start = this.now = this.cur(),
                this.end = i, this.unit = o || (ut.cssNumber[n] ? "" : "px");
        },
        cur: function() {
            var t = q.propHooks[this.prop];
            return t && t.get ? t.get(this) : q.propHooks._default.get(this);
        },
        run: function(t) {
            var e, n = q.propHooks[this.prop];
            return this.pos = e = this.options.duration ? ut.easing[this.easing](t, this.options.duration * t, 0, 1, this.options.duration) : t,
                this.now = (this.end - this.start) * e + this.start, this.options.step && this.options.step.call(this.elem, this.now, this),
                n && n.set ? n.set(this) : q.propHooks._default.set(this), this;
        }
    }, q.prototype.init.prototype = q.prototype, q.propHooks = {
        _default: {
            get: function(t) {
                var e;
                return null == t.elem[t.prop] || t.elem.style && null != t.elem.style[t.prop] ? (e = ut.css(t.elem, t.prop, ""),
                    e && "auto" !== e ? e : 0) : t.elem[t.prop];
            },
            set: function(t) {
                ut.fx.step[t.prop] ? ut.fx.step[t.prop](t) : t.elem.style && (null != t.elem.style[ut.cssProps[t.prop]] || ut.cssHooks[t.prop]) ? ut.style(t.elem, t.prop, t.now + t.unit) : t.elem[t.prop] = t.now;
            }
        }
    }, q.propHooks.scrollTop = q.propHooks.scrollLeft = {
        set: function(t) {
            t.elem.nodeType && t.elem.parentNode && (t.elem[t.prop] = t.now);
        }
    }, ut.each([ "toggle", "show", "hide" ], function(t, e) {
        var n = ut.fn[e];
        ut.fn[e] = function(t, i, r) {
            return null == t || "boolean" == typeof t ? n.apply(this, arguments) : this.animate(H(e, !0), t, i, r);
        };
    }), ut.fn.extend({
        fadeTo: function(t, e, n, i) {
            return this.filter(_).css("opacity", 0).show().end().animate({
                opacity: e
            }, t, n, i);
        },
        animate: function(t, e, n, i) {
            var r = ut.isEmptyObject(t), o = ut.speed(e, n, i), s = function() {
                var e = R(this, ut.extend({}, t), o);
                s.finish = function() {
                    e.stop(!0);
                }, (r || ut._data(this, "finish")) && e.stop(!0);
            };
            return s.finish = s, r || o.queue === !1 ? this.each(s) : this.queue(o.queue, s);
        },
        stop: function(t, n, i) {
            var r = function(t) {
                var e = t.stop;
                delete t.stop, e(i);
            };
            return "string" != typeof t && (i = n, n = t, t = e), n && t !== !1 && this.queue(t || "fx", []),
                this.each(function() {
                    var e = !0, n = null != t && t + "queueHooks", o = ut.timers, s = ut._data(this);
                    if (n) s[n] && s[n].stop && r(s[n]); else for (n in s) s[n] && s[n].stop && nn.test(n) && r(s[n]);
                    for (n = o.length; n--; ) o[n].elem !== this || null != t && o[n].queue !== t || (o[n].anim.stop(i),
                        e = !1, o.splice(n, 1));
                    (e || !i) && ut.dequeue(this, t);
                });
        },
        finish: function(t) {
            return t !== !1 && (t = t || "fx"), this.each(function() {
                var e, n = ut._data(this), i = n[t + "queue"], r = n[t + "queueHooks"], o = ut.timers, s = i ? i.length : 0;
                for (n.finish = !0, ut.queue(this, t, []), r && r.cur && r.cur.finish && r.cur.finish.call(this),
                         e = o.length; e--; ) o[e].elem === this && o[e].queue === t && (o[e].anim.stop(!0),
                    o.splice(e, 1));
                for (e = 0; s > e; e++) i[e] && i[e].finish && i[e].finish.call(this);
                delete n.finish;
            });
        }
    }), ut.each({
        slideDown: H("show"),
        slideUp: H("hide"),
        slideToggle: H("toggle"),
        fadeIn: {
            opacity: "show"
        },
        fadeOut: {
            opacity: "hide"
        },
        fadeToggle: {
            opacity: "toggle"
        }
    }, function(t, e) {
        ut.fn[t] = function(t, n, i) {
            return this.animate(e, t, n, i);
        };
    }), ut.speed = function(t, e, n) {
        var i = t && "object" == typeof t ? ut.extend({}, t) : {
            complete: n || !n && e || ut.isFunction(t) && t,
            duration: t,
            easing: n && e || e && !ut.isFunction(e) && e
        };
        return i.duration = ut.fx.off ? 0 : "number" == typeof i.duration ? i.duration : i.duration in ut.fx.speeds ? ut.fx.speeds[i.duration] : ut.fx.speeds._default,
        (null == i.queue || i.queue === !0) && (i.queue = "fx"), i.old = i.complete, i.complete = function() {
            ut.isFunction(i.old) && i.old.call(this), i.queue && ut.dequeue(this, i.queue);
        }, i;
    }, ut.easing = {
        linear: function(t) {
            return t;
        },
        swing: function(t) {
            return .5 - Math.cos(t * Math.PI) / 2;
        }
    }, ut.timers = [], ut.fx = q.prototype.init, ut.fx.tick = function() {
        var t, n = ut.timers, i = 0;
        for (Qe = ut.now(); n.length > i; i++) t = n[i], t() || n[i] !== t || n.splice(i--, 1);
        n.length || ut.fx.stop(), Qe = e;
    }, ut.fx.timer = function(t) {
        t() && ut.timers.push(t) && ut.fx.start();
    }, ut.fx.interval = 13, ut.fx.start = function() {
        Ke || (Ke = setInterval(ut.fx.tick, ut.fx.interval));
    }, ut.fx.stop = function() {
        clearInterval(Ke), Ke = null;
    }, ut.fx.speeds = {
        slow: 600,
        fast: 200,
        _default: 400
    }, ut.fx.step = {}, ut.expr && ut.expr.filters && (ut.expr.filters.animated = function(t) {
        return ut.grep(ut.timers, function(e) {
            return t === e.elem;
        }).length;
    }), ut.fn.offset = function(t) {
        if (arguments.length) return t === e ? this : this.each(function(e) {
            ut.offset.setOffset(this, t, e);
        });
        var n, i, r = {
            top: 0,
            left: 0
        }, o = this[0], s = o && o.ownerDocument;
        return s ? (n = s.documentElement, ut.contains(n, o) ? (typeof o.getBoundingClientRect !== X && (r = o.getBoundingClientRect()),
            i = U(s), {
            top: r.top + (i.pageYOffset || n.scrollTop) - (n.clientTop || 0),
            left: r.left + (i.pageXOffset || n.scrollLeft) - (n.clientLeft || 0)
        }) : r) : void 0;
    }, ut.offset = {
        setOffset: function(t, e, n) {
            var i = ut.css(t, "position");
            "static" === i && (t.style.position = "relative");
            var r, o, s = ut(t), a = s.offset(), u = ut.css(t, "top"), c = ut.css(t, "left"), l = ("absolute" === i || "fixed" === i) && ut.inArray("auto", [ u, c ]) > -1, h = {}, f = {};
            l ? (f = s.position(), r = f.top, o = f.left) : (r = parseFloat(u) || 0, o = parseFloat(c) || 0),
            ut.isFunction(e) && (e = e.call(t, n, a)), null != e.top && (h.top = e.top - a.top + r),
            null != e.left && (h.left = e.left - a.left + o), "using" in e ? e.using.call(t, h) : s.css(h);
        }
    }, ut.fn.extend({
        position: function() {
            if (this[0]) {
                var t, e, n = {
                    top: 0,
                    left: 0
                }, i = this[0];
                return "fixed" === ut.css(i, "position") ? e = i.getBoundingClientRect() : (t = this.offsetParent(),
                    e = this.offset(), ut.nodeName(t[0], "html") || (n = t.offset()), n.top += ut.css(t[0], "borderTopWidth", !0),
                    n.left += ut.css(t[0], "borderLeftWidth", !0)), {
                    top: e.top - n.top - ut.css(i, "marginTop", !0),
                    left: e.left - n.left - ut.css(i, "marginLeft", !0)
                };
            }
        },
        offsetParent: function() {
            return this.map(function() {
                for (var t = this.offsetParent || G.documentElement; t && !ut.nodeName(t, "html") && "static" === ut.css(t, "position"); ) t = t.offsetParent;
                return t || G.documentElement;
            });
        }
    }), ut.each({
        scrollLeft: "pageXOffset",
        scrollTop: "pageYOffset"
    }, function(t, n) {
        var i = /Y/.test(n);
        ut.fn[t] = function(r) {
            return ut.access(this, function(t, r, o) {
                var s = U(t);
                return o === e ? s ? n in s ? s[n] : s.document.documentElement[r] : t[r] : (s ? s.scrollTo(i ? ut(s).scrollLeft() : o, i ? o : ut(s).scrollTop()) : t[r] = o,
                    e);
            }, t, r, arguments.length, null);
        };
    }), ut.each({
        Height: "height",
        Width: "width"
    }, function(t, n) {
        ut.each({
            padding: "inner" + t,
            content: n,
            "": "outer" + t
        }, function(i, r) {
            ut.fn[r] = function(r, o) {
                var s = arguments.length && (i || "boolean" != typeof r), a = i || (r === !0 || o === !0 ? "margin" : "border");
                return ut.access(this, function(n, i, r) {
                    var o;
                    return ut.isWindow(n) ? n.document.documentElement["client" + t] : 9 === n.nodeType ? (o = n.documentElement,
                        Math.max(n.body["scroll" + t], o["scroll" + t], n.body["offset" + t], o["offset" + t], o["client" + t])) : r === e ? ut.css(n, i, a) : ut.style(n, i, r, a);
                }, n, s ? r : e, s, null);
            };
        });
    }), t.jQuery = t.$ = ut, "function" == typeof define && define.amd && define.amd.jQuery && define("jquery", [], function() {
        return ut;
    });
}(window), function($) {
    "use strict";
    var escape = /["\\\x00-\x1f\x7f-\x9f]/g, meta = {
        "\b": "\\b",
        "	": "\\t",
        "\n": "\\n",
        "\f": "\\f",
        "\r": "\\r",
        '"': '\\"',
        "\\": "\\\\"
    }, hasOwn = Object.prototype.hasOwnProperty;
    $.toJSON = "object" == typeof JSON && JSON.stringify ? JSON.stringify : function(t) {
        if (null === t) return "null";
        var e, n, i, r, o = $.type(t);
        if ("undefined" === o) return void 0;
        if ("number" === o || "boolean" === o) return String(t);
        if ("string" === o) return $.quoteString(t);
        if ("function" == typeof t.toJSON) return $.toJSON(t.toJSON());
        if ("date" === o) {
            var s = t.getUTCMonth() + 1, a = t.getUTCDate(), u = t.getUTCFullYear(), c = t.getUTCHours(), l = t.getUTCMinutes(), h = t.getUTCSeconds(), f = t.getUTCMilliseconds();
            return 10 > s && (s = "0" + s), 10 > a && (a = "0" + a), 10 > c && (c = "0" + c),
            10 > l && (l = "0" + l), 10 > h && (h = "0" + h), 100 > f && (f = "0" + f), 10 > f && (f = "0" + f),
            '"' + u + "-" + s + "-" + a + "T" + c + ":" + l + ":" + h + "." + f + 'Z"';
        }
        if (e = [], $.isArray(t)) {
            for (n = 0; n < t.length; n++) e.push($.toJSON(t[n]) || "null");
            return "[" + e.join(",") + "]";
        }
        if ("object" == typeof t) {
            for (n in t) if (hasOwn.call(t, n)) {
                if (o = typeof n, "number" === o) i = '"' + n + '"'; else {
                    if ("string" !== o) continue;
                    i = $.quoteString(n);
                }
                o = typeof t[n], "function" !== o && "undefined" !== o && (r = $.toJSON(t[n]), e.push(i + ":" + r));
            }
            return "{" + e.join(",") + "}";
        }
    }, $.evalJSON = "object" == typeof JSON && JSON.parse ? JSON.parse : function(str) {
        return eval("(" + str + ")");
    }, $.secureEvalJSON = "object" == typeof JSON && JSON.parse ? JSON.parse : function(str) {
        var filtered = str.replace(/\\["\\\/bfnrtu]/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, "");
        if (/^[\],:{}\s]*$/.test(filtered)) return eval("(" + str + ")");
        throw new SyntaxError("Error parsing JSON, source is not valid.");
    }, $.quoteString = function(t) {
        return t.match(escape) ? '"' + t.replace(escape, function(t) {
            var e = meta[t];
            return "string" == typeof e ? e : (e = t.charCodeAt(), "\\u00" + Math.floor(e / 16).toString(16) + (e % 16).toString(16));
        }) + '"' : '"' + t + '"';
    };
}(jQuery), window.matchMedia || (window.matchMedia = function() {
    "use strict";
    var t = window.styleMedia || window.media;
    if (!t) {
        var e = document.createElement("style"), n = document.getElementsByTagName("script")[0], i = null;
        e.type = "text/css", e.id = "matchmediajs-test", n.parentNode.insertBefore(e, n),
            i = "getComputedStyle" in window && window.getComputedStyle(e, null) || e.currentStyle,
            t = {
                matchMedium: function(t) {
                    var n = "@media " + t + "{ #matchmediajs-test { width: 1px; } }";
                    return e.styleSheet ? e.styleSheet.cssText = n : e.textContent = n, "1px" === i.width;
                }
            };
    }
    return function(e) {
        return {
            matches: t.matchMedium(e || "all"),
            media: e || "all"
        };
    };
}()), window.Ladda = function() {
    function t(t) {
        if ("undefined" == typeof t) throw "Button target must be defined.";
        var e = {
            start: function() {
                return t.setAttribute("disabled", ""), t.setAttribute("data-loading", ""), this;
            },
            stop: function(e) {
                return t.removeAttribute("disabled", ""), t.removeAttribute("data-loading"), this;
            },
            toggle: function() {
                this.isLoading() ? this.stop() : this.start();
            },
            isLoading: function() {
                return t.hasAttribute("data-loading");
            }
        };
        return i.push(e), e;
    }
    function e(t, e) {
        e = e || {};
        var n = [];
        "string" == typeof t ? n = [].slice.call(document.querySelectorAll(t)) : "object" == typeof t && "string" == typeof t.nodeName && (n = [ n ]);
        for (var i = 0, r = n.length; r > i; i++) !function() {
            var t = n[i];
            if ("function" == typeof t.addEventListener) {
                var r = Ladda.create(t), o = -1;
                t.addEventListener("click", function() {
                    r.start(), "number" == typeof e.timeout && (clearTimeout(o), o = setTimeout(r.stop, 2e3));
                }, !1);
            }
        }();
    }
    function n() {
        for (var t = 0, e = i.length; e > t; t++) i[t].stop();
    }
    var i = [];
    return {
        bind: e,
        create: t,
        stopAll: n
    };
}(), window.Modernizr = function(t, e, n) {
    function i(t) {
        b.cssText = t;
    }
    function r(t, e) {
        return i(k.join(t + ";") + (e || ""));
    }
    function o(t, e) {
        return typeof t === e;
    }
    function s(t, e) {
        return !!~("" + t).indexOf(e);
    }
    function a(t, e) {
        for (var i in t) {
            var r = t[i];
            if (!s(r, "-") && b[r] !== n) return "pfx" == e ? r : !0;
        }
        return !1;
    }
    function u(t, e, i) {
        for (var r in t) {
            var s = e[t[r]];
            if (s !== n) return i === !1 ? t[r] : o(s, "function") ? s.bind(i || e) : s;
        }
        return !1;
    }
    function c(t, e, n) {
        var i = t.charAt(0).toUpperCase() + t.slice(1), r = (t + " " + E.join(i + " ") + i).split(" ");
        return o(e, "string") || o(e, "undefined") ? a(r, e) : (r = (t + " " + T.join(i + " ") + i).split(" "),
            u(r, e, n));
    }
    function l() {
        p.input = function(n) {
            for (var i = 0, r = n.length; r > i; i++) F[n[i]] = n[i] in x;
            return F.list && (F.list = !!e.createElement("datalist") && !!t.HTMLDataListElement),
                F;
        }("autocomplete autofocus list placeholder max min multiple pattern required step".split(" ")),
            p.inputtypes = function(t) {
                for (var i, r, o, s = 0, a = t.length; a > s; s++) x.setAttribute("type", r = t[s]),
                    i = "text" !== x.type, i && (x.value = w, x.style.cssText = "position:absolute;visibility:hidden;",
                    /^range$/.test(r) && x.style.WebkitAppearance !== n ? (g.appendChild(x), o = e.defaultView,
                        i = o.getComputedStyle && "textfield" !== o.getComputedStyle(x, null).WebkitAppearance && 0 !== x.offsetHeight,
                        g.removeChild(x)) : /^(search|tel)$/.test(r) || (i = /^(url|email)$/.test(r) ? x.checkValidity && x.checkValidity() === !1 : x.value != w)),
                    S[t[s]] = !!i;
                return S;
            }("search tel url email datetime date month week time datetime-local number range color".split(" "));
    }
    var h, f, d = "2.6.2", p = {}, m = !0, g = e.documentElement, v = "modernizr", y = e.createElement(v), b = y.style, x = e.createElement("input"), w = ":)", _ = {}.toString, k = " -webkit- -moz- -o- -ms- ".split(" "), C = "Webkit Moz O ms", E = C.split(" "), T = C.toLowerCase().split(" "), A = {
        svg: "http://www.w3.org/2000/svg"
    }, N = {}, S = {}, F = {}, $ = [], j = $.slice, M = function(t, n, i, r) {
        var o, s, a, u, c = e.createElement("div"), l = e.body, h = l || e.createElement("body");
        if (parseInt(i, 10)) for (;i--; ) a = e.createElement("div"), a.id = r ? r[i] : v + (i + 1),
            c.appendChild(a);
        return o = [ "&#173;", '<style id="s', v, '">', t, "</style>" ].join(""), c.id = v,
            (l ? c : h).innerHTML += o, h.appendChild(c), l || (h.style.background = "", h.style.overflow = "hidden",
            u = g.style.overflow, g.style.overflow = "hidden", g.appendChild(h)), s = n(c, t),
            l ? c.parentNode.removeChild(c) : (h.parentNode.removeChild(h), g.style.overflow = u),
            !!s;
    }, D = function() {
        function t(t, r) {
            r = r || e.createElement(i[t] || "div"), t = "on" + t;
            var s = t in r;
            return s || (r.setAttribute || (r = e.createElement("div")), r.setAttribute && r.removeAttribute && (r.setAttribute(t, ""),
                s = o(r[t], "function"), o(r[t], "undefined") || (r[t] = n), r.removeAttribute(t))),
                r = null, s;
        }
        var i = {
            select: "input",
            change: "input",
            submit: "form",
            reset: "form",
            error: "img",
            load: "img",
            abort: "img"
        };
        return t;
    }(), L = {}.hasOwnProperty;
    f = o(L, "undefined") || o(L.call, "undefined") ? function(t, e) {
        return e in t && o(t.constructor.prototype[e], "undefined");
    } : function(t, e) {
        return L.call(t, e);
    }, Function.prototype.bind || (Function.prototype.bind = function(t) {
        var e = this;
        if ("function" != typeof e) throw new TypeError();
        var n = j.call(arguments, 1), i = function() {
            if (this instanceof i) {
                var r = function() {};
                r.prototype = e.prototype;
                var o = new r(), s = e.apply(o, n.concat(j.call(arguments)));
                return Object(s) === s ? s : o;
            }
            return e.apply(t, n.concat(j.call(arguments)));
        };
        return i;
    }), N.flexbox = function() {
        return c("flexWrap");
    }, N.flexboxlegacy = function() {
        return c("boxDirection");
    }, N.canvas = function() {
        var t = e.createElement("canvas");
        return !!t.getContext && !!t.getContext("2d");
    }, N.canvastext = function() {
        return !!p.canvas && !!o(e.createElement("canvas").getContext("2d").fillText, "function");
    }, N.webgl = function() {
        return !!t.WebGLRenderingContext;
    }, N.touch = function() {
        var n;
        return "ontouchstart" in t || t.DocumentTouch && e instanceof DocumentTouch ? n = !0 : M([ "@media (", k.join("touch-enabled),("), v, ")", "{#modernizr{top:9px;position:absolute}}" ].join(""), function(t) {
            n = 9 === t.offsetTop;
        }), n;
    }, N.geolocation = function() {
        return "geolocation" in navigator;
    }, N.postmessage = function() {
        return !!t.postMessage;
    }, N.websqldatabase = function() {
        return !!t.openDatabase;
    }, N.indexedDB = function() {
        return !!c("indexedDB", t);
    }, N.hashchange = function() {
        return D("hashchange", t) && (e.documentMode === n || e.documentMode > 7);
    }, N.history = function() {
        return !!t.history && !!history.pushState;
    }, N.draganddrop = function() {
        var t = e.createElement("div");
        return "draggable" in t || "ondragstart" in t && "ondrop" in t;
    }, N.websockets = function() {
        return "WebSocket" in t || "MozWebSocket" in t;
    }, N.rgba = function() {
        return i("background-color:rgba(150,255,150,.5)"), s(b.backgroundColor, "rgba");
    }, N.hsla = function() {
        return i("background-color:hsla(120,40%,100%,.5)"), s(b.backgroundColor, "rgba") || s(b.backgroundColor, "hsla");
    }, N.multiplebgs = function() {
        return i("background:url(https://),url(https://),red url(https://)"), /(url\s*\(.*?){3}/.test(b.background);
    }, N.backgroundsize = function() {
        return c("backgroundSize");
    }, N.borderimage = function() {
        return c("borderImage");
    }, N.borderradius = function() {
        return c("borderRadius");
    }, N.boxshadow = function() {
        return c("boxShadow");
    }, N.textshadow = function() {
        return "" === e.createElement("div").style.textShadow;
    }, N.opacity = function() {
        return r("opacity:.55"), /^0.55$/.test(b.opacity);
    }, N.cssanimations = function() {
        return c("animationName");
    }, N.csscolumns = function() {
        return c("columnCount");
    }, N.cssgradients = function() {
        var t = "background-image:", e = "gradient(linear,left top,right bottom,from(#9f9),to(white));", n = "linear-gradient(left top,#9f9, white);";
        return i((t + "-webkit- ".split(" ").join(e + t) + k.join(n + t)).slice(0, -t.length)),
            s(b.backgroundImage, "gradient");
    }, N.cssreflections = function() {
        return c("boxReflect");
    }, N.csstransforms = function() {
        return !!c("transform");
    }, N.csstransforms3d = function() {
        var t = !!c("perspective");
        return t && "webkitPerspective" in g.style && M("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}", function(e, n) {
            t = 9 === e.offsetLeft && 3 === e.offsetHeight;
        }), t;
    }, N.csstransitions = function() {
        return c("transition");
    }, N.fontface = function() {
        var t;
        return M('@font-face {font-family:"font";src:url("https://")}', function(n, i) {
            var r = e.getElementById("smodernizr"), o = r.sheet || r.styleSheet, s = o ? o.cssRules && o.cssRules[0] ? o.cssRules[0].cssText : o.cssText || "" : "";
            t = /src/i.test(s) && 0 === s.indexOf(i.split(" ")[0]);
        }), t;
    }, N.generatedcontent = function() {
        var t;
        return M([ "#", v, "{font:0/0 a}#", v, ':after{content:"', w, '";visibility:hidden;font:3px/1 a}' ].join(""), function(e) {
            t = e.offsetHeight >= 3;
        }), t;
    }, N.video = function() {
        var t = e.createElement("video"), n = !1;
        try {
            (n = !!t.canPlayType) && (n = new Boolean(n), n.ogg = t.canPlayType('video/ogg; codecs="theora"').replace(/^no$/, ""),
                n.h264 = t.canPlayType('video/mp4; codecs="avc1.42E01E"').replace(/^no$/, ""), n.webm = t.canPlayType('video/webm; codecs="vp8, vorbis"').replace(/^no$/, ""));
        } catch (i) {}
        return n;
    }, N.audio = function() {
        var t = e.createElement("audio"), n = !1;
        try {
            (n = !!t.canPlayType) && (n = new Boolean(n), n.ogg = t.canPlayType('audio/ogg; codecs="vorbis"').replace(/^no$/, ""),
                n.mp3 = t.canPlayType("audio/mpeg;").replace(/^no$/, ""), n.wav = t.canPlayType('audio/wav; codecs="1"').replace(/^no$/, ""),
                n.m4a = (t.canPlayType("audio/x-m4a;") || t.canPlayType("audio/aac;")).replace(/^no$/, ""));
        } catch (i) {}
        return n;
    }, N.localstorage = function() {
        try {
            return localStorage.setItem(v, v), localStorage.removeItem(v), !0;
        } catch (t) {
            return !1;
        }
    }, N.sessionstorage = function() {
        try {
            return sessionStorage.setItem(v, v), sessionStorage.removeItem(v), !0;
        } catch (t) {
            return !1;
        }
    }, N.webworkers = function() {
        return !!t.Worker;
    }, N.applicationcache = function() {
        return !!t.applicationCache;
    }, N.svg = function() {
        return !!e.createElementNS && !!e.createElementNS(A.svg, "svg").createSVGRect;
    }, N.inlinesvg = function() {
        var t = e.createElement("div");
        return t.innerHTML = "<svg/>", (t.firstChild && t.firstChild.namespaceURI) == A.svg;
    }, N.smil = function() {
        return !!e.createElementNS && /SVGAnimate/.test(_.call(e.createElementNS(A.svg, "animate")));
    }, N.svgclippaths = function() {
        return !!e.createElementNS && /SVGClipPath/.test(_.call(e.createElementNS(A.svg, "clipPath")));
    };
    for (var O in N) f(N, O) && (h = O.toLowerCase(), p[h] = N[O](), $.push((p[h] ? "" : "no-") + h));
    return p.input || l(), p.addTest = function(t, e) {
        if ("object" == typeof t) for (var i in t) f(t, i) && p.addTest(i, t[i]); else {
            if (t = t.toLowerCase(), p[t] !== n) return p;
            e = "function" == typeof e ? e() : e, "undefined" != typeof m && m && (g.className += " " + (e ? "" : "no-") + t),
                p[t] = e;
        }
        return p;
    }, i(""), y = x = null, function(t, e) {
        function n(t, e) {
            var n = t.createElement("p"), i = t.getElementsByTagName("head")[0] || t.documentElement;
            return n.innerHTML = "x<style>" + e + "</style>", i.insertBefore(n.lastChild, i.firstChild);
        }
        function i() {
            var t = v.elements;
            return "string" == typeof t ? t.split(" ") : t;
        }
        function r(t) {
            var e = g[t[p]];
            return e || (e = {}, m++, t[p] = m, g[m] = e), e;
        }
        function o(t, n, i) {
            if (n || (n = e), l) return n.createElement(t);
            i || (i = r(n));
            var o;
            return o = i.cache[t] ? i.cache[t].cloneNode() : d.test(t) ? (i.cache[t] = i.createElem(t)).cloneNode() : i.createElem(t),
                o.canHaveChildren && !f.test(t) ? i.frag.appendChild(o) : o;
        }
        function s(t, n) {
            if (t || (t = e), l) return t.createDocumentFragment();
            n = n || r(t);
            for (var o = n.frag.cloneNode(), s = 0, a = i(), u = a.length; u > s; s++) o.createElement(a[s]);
            return o;
        }
        function a(t, e) {
            e.cache || (e.cache = {}, e.createElem = t.createElement, e.createFrag = t.createDocumentFragment,
                e.frag = e.createFrag()), t.createElement = function(n) {
                return v.shivMethods ? o(n, t, e) : e.createElem(n);
            }, t.createDocumentFragment = Function("h,f", "return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&(" + i().join().replace(/\w+/g, function(t) {
                    return e.createElem(t), e.frag.createElement(t), 'c("' + t + '")';
                }) + ");return n}")(v, e.frag);
        }
        function u(t) {
            t || (t = e);
            var i = r(t);
            return v.shivCSS && !c && !i.hasCSS && (i.hasCSS = !!n(t, "article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}")),
            l || a(t, i), t;
        }
        var c, l, h = t.html5 || {}, f = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i, d = /^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i, p = "_html5shiv", m = 0, g = {};
        !function() {
            try {
                var t = e.createElement("a");
                t.innerHTML = "<xyz></xyz>", c = "hidden" in t, l = 1 == t.childNodes.length || function() {
                        e.createElement("a");
                        var t = e.createDocumentFragment();
                        return "undefined" == typeof t.cloneNode || "undefined" == typeof t.createDocumentFragment || "undefined" == typeof t.createElement;
                    }();
            } catch (n) {
                c = !0, l = !0;
            }
        }();
        var v = {
            elements: h.elements || "abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",
            shivCSS: h.shivCSS !== !1,
            supportsUnknownElements: l,
            shivMethods: h.shivMethods !== !1,
            type: "default",
            shivDocument: u,
            createElement: o,
            createDocumentFragment: s
        };
        t.html5 = v, u(e);
    }(this, e), p._version = d, p._prefixes = k, p._domPrefixes = T, p._cssomPrefixes = E,
        p.hasEvent = D, p.testProp = function(t) {
        return a([ t ]);
    }, p.testAllProps = c, p.testStyles = M, g.className = g.className.replace(/(^|\s)no-js(\s|$)/, "$1$2") + (m ? " js " + $.join(" ") : ""),
        p;
}(this, this.document), function(t, e, n) {
    function i(t) {
        return "[object Function]" == g.call(t);
    }
    function r(t) {
        return "string" == typeof t;
    }
    function o() {}
    function s(t) {
        return !t || "loaded" == t || "complete" == t || "uninitialized" == t;
    }
    function a() {
        var t = v.shift();
        y = 1, t ? t.t ? p(function() {
            ("c" == t.t ? f.injectCss : f.injectJs)(t.s, 0, t.a, t.x, t.e, 1);
        }, 0) : (t(), a()) : y = 0;
    }
    function u(t, n, i, r, o, u, c) {
        function l(e) {
            if (!d && s(h.readyState) && (b.r = d = 1, !y && a(), h.onload = h.onreadystatechange = null,
                    e)) {
                "img" != t && p(function() {
                    w.removeChild(h);
                }, 50);
                for (var i in T[n]) T[n].hasOwnProperty(i) && T[n][i].onload();
            }
        }
        var c = c || f.errorTimeout, h = e.createElement(t), d = 0, g = 0, b = {
            t: i,
            s: n,
            e: o,
            a: u,
            x: c
        };
        1 === T[n] && (g = 1, T[n] = []), "object" == t ? h.data = n : (h.src = n, h.type = t),
            h.width = h.height = "0", h.onerror = h.onload = h.onreadystatechange = function() {
            l.call(this, g);
        }, v.splice(r, 0, b), "img" != t && (g || 2 === T[n] ? (w.insertBefore(h, x ? null : m),
            p(l, c)) : T[n].push(h));
    }
    function c(t, e, n, i, o) {
        return y = 0, e = e || "j", r(t) ? u("c" == e ? k : _, t, e, this.i++, n, i, o) : (v.splice(this.i++, 0, t),
        1 == v.length && a()), this;
    }
    function l() {
        var t = f;
        return t.loader = {
            load: c,
            i: 0
        }, t;
    }
    var h, f, d = e.documentElement, p = t.setTimeout, m = e.getElementsByTagName("script")[0], g = {}.toString, v = [], y = 0, b = "MozAppearance" in d.style, x = b && !!e.createRange().compareNode, w = x ? d : m.parentNode, d = t.opera && "[object Opera]" == g.call(t.opera), d = !!e.attachEvent && !d, _ = b ? "object" : d ? "script" : "img", k = d ? "script" : _, C = Array.isArray || function(t) {
            return "[object Array]" == g.call(t);
        }, E = [], T = {}, A = {
        timeout: function(t, e) {
            return e.length && (t.timeout = e[0]), t;
        }
    };
    f = function(t) {
        function e(t) {
            var e, n, i, t = t.split("!"), r = E.length, o = t.pop(), s = t.length, o = {
                url: o,
                origUrl: o,
                prefixes: t
            };
            for (n = 0; s > n; n++) i = t[n].split("="), (e = A[i.shift()]) && (o = e(o, i));
            for (n = 0; r > n; n++) o = E[n](o);
            return o;
        }
        function s(t, r, o, s, a) {
            var u = e(t), c = u.autoCallback;
            u.url.split(".").pop().split("?").shift(), u.bypass || (r && (r = i(r) ? r : r[t] || r[s] || r[t.split("/").pop().split("?")[0]]),
                u.instead ? u.instead(t, r, o, s, a) : (T[u.url] ? u.noexec = !0 : T[u.url] = 1,
                    o.load(u.url, u.forceCSS || !u.forceJS && "css" == u.url.split(".").pop().split("?").shift() ? "c" : n, u.noexec, u.attrs, u.timeout),
                (i(r) || i(c)) && o.load(function() {
                    l(), r && r(u.origUrl, a, s), c && c(u.origUrl, a, s), T[u.url] = 2;
                })));
        }
        function a(t, e) {
            function n(t, n) {
                if (t) {
                    if (r(t)) n || (h = function() {
                        var t = [].slice.call(arguments);
                        f.apply(this, t), d();
                    }), s(t, h, e, 0, c); else if (Object(t) === t) for (u in a = function() {
                        var e, n = 0;
                        for (e in t) t.hasOwnProperty(e) && n++;
                        return n;
                    }(), t) t.hasOwnProperty(u) && (!n && !--a && (i(h) ? h = function() {
                        var t = [].slice.call(arguments);
                        f.apply(this, t), d();
                    } : h[u] = function(t) {
                        return function() {
                            var e = [].slice.call(arguments);
                            t && t.apply(this, e), d();
                        };
                    }(f[u])), s(t[u], h, e, u, c));
                } else !n && d();
            }
            var a, u, c = !!t.test, l = t.load || t.both, h = t.callback || o, f = h, d = t.complete || o;
            n(c ? t.yep : t.nope, !!l), l && n(l);
        }
        var u, c, h = this.yepnope.loader;
        if (r(t)) s(t, 0, h, 0); else if (C(t)) for (u = 0; u < t.length; u++) c = t[u],
            r(c) ? s(c, 0, h, 0) : C(c) ? f(c) : Object(c) === c && a(c, h); else Object(t) === t && a(t, h);
    }, f.addPrefix = function(t, e) {
        A[t] = e;
    }, f.addFilter = function(t) {
        E.push(t);
    }, f.errorTimeout = 1e4, null == e.readyState && e.addEventListener && (e.readyState = "loading",
        e.addEventListener("DOMContentLoaded", h = function() {
            e.removeEventListener("DOMContentLoaded", h, 0), e.readyState = "complete";
        }, 0)), t.yepnope = l(), t.yepnope.executeStack = a, t.yepnope.injectJs = function(t, n, i, r, u, c) {
        var l, h, d = e.createElement("script"), r = r || f.errorTimeout;
        d.src = t;
        for (h in i) d.setAttribute(h, i[h]);
        n = c ? a : n || o, d.onreadystatechange = d.onload = function() {
            !l && s(d.readyState) && (l = 1, n(), d.onload = d.onreadystatechange = null);
        }, p(function() {
            l || (l = 1, n(1));
        }, r), u ? d.onload() : m.parentNode.insertBefore(d, m);
    }, t.yepnope.injectCss = function(t, n, i, r, s, u) {
        var c, r = e.createElement("link"), n = u ? a : n || o;
        r.href = t, r.rel = "stylesheet", r.type = "text/css";
        for (c in i) r.setAttribute(c, i[c]);
        s || (m.parentNode.insertBefore(r, m), p(n, 0));
    };
}(this, document), Modernizr.load = function() {
    yepnope.apply(window, [].slice.call(arguments, 0));
}, function(t) {
    String.prototype.trim === t && (String.prototype.trim = function() {
        return this.replace(/^\s+/, "").replace(/\s+$/, "");
    }), Array.prototype.reduce === t && (Array.prototype.reduce = function(e) {
        if (void 0 === this || null === this) throw new TypeError();
        var n, i = Object(this), r = i.length >>> 0, o = 0;
        if ("function" != typeof e) throw new TypeError();
        if (0 == r && 1 == arguments.length) throw new TypeError();
        if (2 <= arguments.length) n = arguments[1]; else for (;;) {
            if (o in i) {
                n = i[o++];
                break;
            }
            if (++o >= r) throw new TypeError();
        }
        for (;r > o; ) o in i && (n = e.call(t, n, i[o], o, i)), o++;
        return n;
    });
}();

var Zepto = function() {
    function t(t) {
        return "[object Function]" == R.call(t);
    }
    function e(t) {
        return t instanceof Object;
    }
    function n(t) {
        return e(t) && t.__proto__ == Object.prototype;
    }
    function i(t) {
        return t instanceof Array;
    }
    function r(t) {
        return "number" == typeof t.length;
    }
    function o(t) {
        return t.replace(/::/g, "/").replace(/([A-Z]+)([A-Z][a-z])/g, "$1_$2").replace(/([a-z\d])([A-Z])/g, "$1_$2").replace(/_/g, "-").toLowerCase();
    }
    function s(t) {
        return t in E ? E[t] : E[t] = RegExp("(^|\\s)" + t + "(\\s|$)");
    }
    function a(t) {
        return "children" in t ? w.call(t.children) : g.map(t.childNodes, function(t) {
            return 1 == t.nodeType ? t : void 0;
        });
    }
    function u(t, e, i) {
        for (m in e) i && n(e[m]) ? (n(t[m]) || (t[m] = {}), u(t[m], e[m], i)) : e[m] !== p && (t[m] = e[m]);
    }
    function c(t, e) {
        return e === p ? g(t) : g(t).filter(e);
    }
    function l(e, n, i, r) {
        return t(n) ? n.call(e, i, r) : n;
    }
    function h(t, e) {
        var n = t.className, i = n && n.baseVal !== p;
        return e === p ? i ? n.baseVal : n : void (i ? n.baseVal = e : t.className = e);
    }
    function f(t) {
        var e;
        try {
            return t ? "true" == t || ("false" == t ? !1 : "null" == t ? null : isNaN(e = Number(t)) ? /^[\[\{]/.test(t) ? g.parseJSON(t) : t : e) : t;
        } catch (n) {
            return t;
        }
    }
    function d(t, e) {
        e(t);
        for (var n in t.childNodes) d(t.childNodes[n], e);
    }
    var p, m, g, v, y, b, x = [], w = x.slice, _ = x.filter, k = window.document, C = {}, E = {}, T = k.defaultView.getComputedStyle, A = {
        "column-count": 1,
        columns: 1,
        "font-weight": 1,
        "line-height": 1,
        opacity: 1,
        "z-index": 1,
        zoom: 1
    }, N = /^\s*<(\w+|!)[^>]*>/, S = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi, F = /^(?:body|html)$/i, $ = "val css html text data width height offset".split(" "), j = k.createElement("table"), M = k.createElement("tr"), D = {
        tr: k.createElement("tbody"),
        tbody: j,
        thead: j,
        tfoot: j,
        td: M,
        th: M,
        "*": k.createElement("div")
    }, L = /complete|loaded|interactive/, O = /^\.([\w-]+)$/, P = /^#([\w-]*)$/, B = /^[\w-]+$/, R = {}.toString, z = {}, I = k.createElement("div");
    return z.matches = function(t, e) {
        if (!t || 1 !== t.nodeType) return !1;
        var n = t.webkitMatchesSelector || t.mozMatchesSelector || t.oMatchesSelector || t.matchesSelector;
        if (n) return n.call(t, e);
        var i;
        return i = t.parentNode, (n = !i) && (i = I).appendChild(t), i = ~z.qsa(i, e).indexOf(t),
        n && I.removeChild(t), i;
    }, y = function(t) {
        return t.replace(/-+(.)?/g, function(t, e) {
            return e ? e.toUpperCase() : "";
        });
    }, b = function(t) {
        return _.call(t, function(e, n) {
            return t.indexOf(e) == n;
        });
    }, z.fragment = function(t, e, i) {
        t.replace && (t = t.replace(S, "<$1></$2>")), e === p && (e = N.test(t) && RegExp.$1),
        e in D || (e = "*");
        var r, o = D[e];
        return o.innerHTML = "" + t, t = g.each(w.call(o.childNodes), function() {
            o.removeChild(this);
        }), n(i) && (r = g(t), g.each(i, function(t, e) {
            -1 < $.indexOf(t) ? r[t](e) : r.attr(t, e);
        })), t;
    }, z.Z = function(t, e) {
        return t = t || [], t.__proto__ = arguments.callee.prototype, t.selector = e || "",
            t;
    }, z.isZ = function(t) {
        return t instanceof z.Z;
    }, z.init = function(r, o) {
        if (r) {
            if (t(r)) return g(k).ready(r);
            if (z.isZ(r)) return r;
            var s;
            if (i(r)) s = _.call(r, function(t) {
                return t !== p && null !== t;
            }); else if (e(r)) s = [ n(r) ? g.extend({}, r) : r ], r = null; else if (N.test(r)) s = z.fragment(r.trim(), RegExp.$1, o),
                r = null; else {
                if (o !== p) return g(o).find(r);
                s = z.qsa(k, r);
            }
            return z.Z(s, r);
        }
        return z.Z();
    }, g = function(t, e) {
        return z.init(t, e);
    }, g.extend = function(t) {
        var e, n = w.call(arguments, 1);
        return "boolean" == typeof t && (e = t, t = n.shift()), n.forEach(function(n) {
            u(t, n, e);
        }), t;
    }, z.qsa = function(t, e) {
        var n;
        return t === k && P.test(e) ? (n = t.getElementById(RegExp.$1)) ? [ n ] : [] : 1 !== t.nodeType && 9 !== t.nodeType ? [] : w.call(O.test(e) ? t.getElementsByClassName(RegExp.$1) : B.test(e) ? t.getElementsByTagName(e) : t.querySelectorAll(e));
    }, g.contains = function(t, e) {
        return t !== e && t.contains(e);
    }, g.isFunction = t, g.isObject = e, g.isArray = i, g.isPlainObject = n, g.inArray = function(t, e, n) {
        return x.indexOf.call(e, t, n);
    }, g.camelCase = y, g.trim = function(t) {
        return t.trim();
    }, g.uuid = 0, g.support = {}, g.expr = {}, g.map = function(t, e) {
        var n, i, o = [];
        if (r(t)) for (i = 0; i < t.length; i++) n = e(t[i], i), null != n && o.push(n); else for (i in t) n = e(t[i], i),
        null != n && o.push(n);
        return 0 < o.length ? g.fn.concat.apply([], o) : o;
    }, g.each = function(t, e) {
        var n;
        if (r(t)) for (n = 0; n < t.length && !1 !== e.call(t[n], n, t[n]); n++) ; else for (n in t) if (!1 === e.call(t[n], n, t[n])) break;
        return t;
    }, g.grep = function(t, e) {
        return _.call(t, e);
    }, window.JSON && (g.parseJSON = JSON.parse), g.fn = {
        forEach: x.forEach,
        reduce: x.reduce,
        push: x.push,
        sort: x.sort,
        indexOf: x.indexOf,
        concat: x.concat,
        map: function(t) {
            return g(g.map(this, function(e, n) {
                return t.call(e, n, e);
            }));
        },
        slice: function() {
            return g(w.apply(this, arguments));
        },
        ready: function(t) {
            return L.test(k.readyState) ? t(g) : k.addEventListener("DOMContentLoaded", function() {
                t(g);
            }, !1), this;
        },
        get: function(t) {
            return t === p ? w.call(this) : this[t];
        },
        toArray: function() {
            return this.get();
        },
        size: function() {
            return this.length;
        },
        remove: function() {
            return this.each(function() {
                null != this.parentNode && this.parentNode.removeChild(this);
            });
        },
        each: function(t) {
            return this.forEach(function(e, n) {
                t.call(e, n, e);
            }), this;
        },
        filter: function(e) {
            return t(e) ? this.not(this.not(e)) : g(_.call(this, function(t) {
                return z.matches(t, e);
            }));
        },
        add: function(t, e) {
            return g(b(this.concat(g(t, e))));
        },
        is: function(t) {
            return 0 < this.length && z.matches(this[0], t);
        },
        not: function(e) {
            var n = [];
            if (t(e) && e.call !== p) this.each(function(t) {
                e.call(this, t) || n.push(this);
            }); else {
                var i = "string" == typeof e ? this.filter(e) : r(e) && t(e.item) ? w.call(e) : g(e);
                this.forEach(function(t) {
                    0 > i.indexOf(t) && n.push(t);
                });
            }
            return g(n);
        },
        has: function(t) {
            return this.filter(function() {
                return e(t) ? g.contains(this, t) : g(this).find(t).size();
            });
        },
        eq: function(t) {
            return -1 === t ? this.slice(t) : this.slice(t, +t + 1);
        },
        first: function() {
            var t = this[0];
            return t && !e(t) ? t : g(t);
        },
        last: function() {
            var t = this[this.length - 1];
            return t && !e(t) ? t : g(t);
        },
        find: function(t) {
            return 1 == this.length ? g(z.qsa(this[0], t)) : this.map(function() {
                return z.qsa(this, t);
            });
        },
        closest: function(t, e) {
            for (var n = this[0]; n && !z.matches(n, t); ) n = n !== e && n !== k && n.parentNode;
            return g(n);
        },
        parents: function(t) {
            for (var e = [], n = this; 0 < n.length; ) n = g.map(n, function(t) {
                return (t = t.parentNode) && t !== k && 0 > e.indexOf(t) ? (e.push(t), t) : void 0;
            });
            return c(e, t);
        },
        parent: function(t) {
            return c(b(this.pluck("parentNode")), t);
        },
        children: function(t) {
            return c(this.map(function() {
                return a(this);
            }), t);
        },
        contents: function() {
            return this.map(function() {
                return w.call(this.childNodes);
            });
        },
        siblings: function(t) {
            return c(this.map(function(t, e) {
                return _.call(a(e.parentNode), function(t) {
                    return t !== e;
                });
            }), t);
        },
        empty: function() {
            return this.each(function() {
                this.innerHTML = "";
            });
        },
        pluck: function(t) {
            return g.map(this, function(e) {
                return e[t];
            });
        },
        show: function() {
            return this.each(function() {
                if ("none" == this.style.display && (this.style.display = null), "none" == T(this, "").getPropertyValue("display")) {
                    var t, e, n = this.style, i = this.nodeName;
                    C[i] || (t = k.createElement(i), k.body.appendChild(t), e = T(t, "").getPropertyValue("display"),
                        t.parentNode.removeChild(t), "none" == e && (e = "block"), C[i] = e), n.display = C[i];
                }
            });
        },
        replaceWith: function(t) {
            return this.before(t).remove();
        },
        wrap: function(e) {
            var n = t(e);
            if (this[0] && !n) var i = g(e).get(0), r = i.parentNode || 1 < this.length;
            return this.each(function(t) {
                g(this).wrapAll(n ? e.call(this, t) : r ? i.cloneNode(!0) : i);
            });
        },
        wrapAll: function(t) {
            if (this[0]) {
                g(this[0]).before(t = g(t));
                for (var e; (e = t.children()).length; ) t = e.first();
                g(t).append(this);
            }
            return this;
        },
        wrapInner: function(e) {
            var n = t(e);
            return this.each(function(t) {
                var i = g(this), r = i.contents();
                t = n ? e.call(this, t) : e, r.length ? r.wrapAll(t) : i.append(t);
            });
        },
        unwrap: function() {
            return this.parent().each(function() {
                g(this).replaceWith(g(this).children());
            }), this;
        },
        clone: function() {
            return this.map(function() {
                return this.cloneNode(!0);
            });
        },
        hide: function() {
            return this.css("display", "none");
        },
        toggle: function(t) {
            return this.each(function() {
                var e = g(this);
                (t === p ? "none" == e.css("display") : t) ? e.show() : e.hide();
            });
        },
        prev: function(t) {
            return g(this.pluck("previousElementSibling")).filter(t || "*");
        },
        next: function(t) {
            return g(this.pluck("nextElementSibling")).filter(t || "*");
        },
        html: function(t) {
            return t === p ? 0 < this.length ? this[0].innerHTML : null : this.each(function(e) {
                var n = this.innerHTML;
                g(this).empty().append(l(this, t, e, n));
            });
        },
        text: function(t) {
            return t === p ? 0 < this.length ? this[0].textContent : null : this.each(function() {
                this.textContent = t;
            });
        },
        attr: function(t, n) {
            var i;
            return "string" == typeof t && n === p ? 0 == this.length || 1 !== this[0].nodeType ? p : "value" == t && "INPUT" == this[0].nodeName ? this.val() : !(i = this[0].getAttribute(t)) && t in this[0] ? this[0][t] : i : this.each(function(i) {
                if (1 === this.nodeType) if (e(t)) for (m in t) {
                    i = m;
                    var r = t[m];
                    null == r ? this.removeAttribute(i) : this.setAttribute(i, r);
                } else i = l(this, n, i, this.getAttribute(t)), null == i ? this.removeAttribute(t) : this.setAttribute(t, i);
            });
        },
        removeAttr: function(t) {
            return this.each(function() {
                1 === this.nodeType && this.removeAttribute(t);
            });
        },
        prop: function(t, e) {
            return e === p ? this[0] ? this[0][t] : p : this.each(function(n) {
                this[t] = l(this, e, n, this[t]);
            });
        },
        data: function(t, e) {
            var n = this.attr("data-" + o(t), e);
            return null !== n ? f(n) : p;
        },
        val: function(t) {
            return t === p ? 0 < this.length ? this[0].multiple ? g(this[0]).find("option").filter(function() {
                return this.selected;
            }).pluck("value") : this[0].value : p : this.each(function(e) {
                this.value = l(this, t, e, this.value);
            });
        },
        offset: function() {
            if (0 == this.length) return null;
            var t = this[0].getBoundingClientRect();
            return {
                left: t.left + window.pageXOffset,
                top: t.top + window.pageYOffset,
                width: t.width,
                height: t.height
            };
        },
        css: function(t, e) {
            if (2 > arguments.length && "string" == typeof t) return 0 == this.length ? p : this[0].style[y(t)] || T(this[0], "").getPropertyValue(t);
            var n = "";
            for (m in t) t[m] || 0 === t[m] ? n += o(m) + ":" + ("number" != typeof t[m] || A[o(m)] ? t[m] : t[m] + "px") + ";" : this.each(function() {
                this.style.removeProperty(o(m));
            });
            return "string" == typeof t && (e || 0 === e ? n = o(t) + ":" + ("number" != typeof e || A[o(t)] ? e : e + "px") : this.each(function() {
                this.style.removeProperty(o(t));
            })), this.each(function() {
                this.style.cssText += ";" + n;
            });
        },
        index: function(t) {
            return t ? this.indexOf(g(t)[0]) : this.parent().children().indexOf(this[0]);
        },
        hasClass: function(t) {
            return 1 > this.length ? !1 : s(t).test(h(this[0]));
        },
        addClass: function(t) {
            return this.each(function(e) {
                v = [];
                var n = h(this);
                l(this, t, e, n).split(/\s+/g).forEach(function(t) {
                    g(this).hasClass(t) || v.push(t);
                }, this), v.length && h(this, n + (n ? " " : "") + v.join(" "));
            });
        },
        removeClass: function(t) {
            return this.each(function(e) {
                return t === p ? h(this, "") : (v = h(this), l(this, t, e, v).split(/\s+/g).forEach(function(t) {
                    v = v.replace(s(t), " ");
                }), void h(this, v.trim()));
            });
        },
        toggleClass: function(t, e) {
            return this.each(function(n) {
                n = l(this, t, n, h(this)), (e === p ? !g(this).hasClass(n) : e) ? g(this).addClass(n) : g(this).removeClass(n);
            });
        },
        scrollTop: function() {
            return this.length ? "scrollTop" in this[0] ? this[0].scrollTop : this[0].scrollY : void 0;
        },
        position: function() {
            if (this.length) {
                var t = this[0], e = this.offsetParent(), n = this.offset(), i = F.test(e[0].nodeName) ? {
                    top: 0,
                    left: 0
                } : e.offset();
                return n.top -= parseFloat(g(t).css("margin-top")) || 0, n.left -= parseFloat(g(t).css("margin-left")) || 0,
                    i.top += parseFloat(g(e[0]).css("border-top-width")) || 0, i.left += parseFloat(g(e[0]).css("border-left-width")) || 0,
                {
                    top: n.top - i.top,
                    left: n.left - i.left
                };
            }
        },
        offsetParent: function() {
            return this.map(function() {
                for (var t = this.offsetParent || k.body; t && !F.test(t.nodeName) && "static" == g(t).css("position"); ) t = t.offsetParent;
                return t;
            });
        }
    }, [ "width", "height" ].forEach(function(t) {
        g.fn[t] = function(e) {
            var n, i = t.replace(/./, function(t) {
                return t[0].toUpperCase();
            });
            return e === p ? this[0] == window ? window["inner" + i] : this[0] == k ? k.documentElement["offset" + i] : (n = this.offset()) && n[t] : this.each(function(n) {
                var i = g(this);
                i.css(t, l(this, e, n, i[t]()));
            });
        };
    }), [ "after", "prepend", "before", "append" ].forEach(function(t, n) {
        var i = n % 2;
        g.fn[t] = function() {
            var t, r = g.map(arguments, function(t) {
                return e(t) ? t : z.fragment(t);
            }), o = 1 < this.length;
            return 1 > r.length ? this : this.each(function(e, s) {
                t = i ? s : s.parentNode, s = 0 == n ? s.nextSibling : 1 == n ? s.firstChild : 2 == n ? s : null,
                    r.forEach(function(e) {
                        if (o) e = e.cloneNode(!0); else if (!t) return g(e).remove();
                        d(t.insertBefore(e, s), function(t) {
                            null != t.nodeName && "SCRIPT" === t.nodeName.toUpperCase() && (!t.type || "text/javascript" === t.type) && !t.src && window.eval.call(window, t.innerHTML);
                        });
                    });
            });
        }, g.fn[i ? t + "To" : "insert" + (n ? "Before" : "After")] = function(e) {
            return g(e)[t](this), this;
        };
    }), z.Z.prototype = g.fn, z.uniq = b, z.deserializeValue = f, g.zepto = z, g;
}();

window.Zepto = Zepto, "$" in window || (window.$ = Zepto), function(t) {
    function e(t) {
        return t._zid || (t._zid = c++);
    }
    function n(t, n, r, o) {
        if (n = i(n), n.ns) var s = RegExp("(?:^| )" + n.ns.replace(" ", " .* ?") + "(?: |$)");
        return (u[e(t)] || []).filter(function(t) {
            return !(!t || n.e && t.e != n.e || n.ns && !s.test(t.ns) || r && e(t.fn) !== e(r) || o && t.sel != o);
        });
    }
    function i(t) {
        return t = ("" + t).split("."), {
            e: t[0],
            ns: t.slice(1).sort().join(" ")
        };
    }
    function r(e, n, i) {
        t.isObject(e) ? t.each(e, i) : e.split(/\s/).forEach(function(t) {
            i(t, n);
        });
    }
    function o(n, o, s, a, c, l) {
        var f = e(n), d = u[f] || (u[f] = []);
        r(o, s, function(e, r) {
            var o = i(e);
            o.fn = r, o.sel = a, o.e in h && (r = function(e) {
                var n = e.relatedTarget;
                return !n || n !== this && !t.contains(this, n) ? o.fn.apply(this, arguments) : void 0;
            }), o.del = c && c(r, e);
            var s = o.del || r;
            o.proxy = function(t) {
                var e = s.apply(n, [ t ].concat(t.data));
                return !1 === e && (t.preventDefault(), t.stopPropagation()), e;
            }, o.i = d.length, d.push(o), n.addEventListener(h[o.e] || o.e, o.proxy, o.del && ("focus" == o.e || "blur" == o.e) || !!l);
        });
    }
    function s(t, i, o, s, a) {
        var c = e(t);
        r(i || "", o, function(e, i) {
            n(t, e, i, s).forEach(function(e) {
                delete u[c][e.i], t.removeEventListener(h[e.e] || e.e, e.proxy, e.del && ("focus" == e.e || "blur" == e.e) || !!a);
            });
        });
    }
    function a(e) {
        var n, i = {
            originalEvent: e
        };
        for (n in e) !p.test(n) && void 0 !== e[n] && (i[n] = e[n]);
        return t.each(m, function(t, n) {
            i[t] = function() {
                return this[n] = f, e[t].apply(e, arguments);
            }, i[n] = d;
        }), i;
    }
    var u = {}, c = 1, l = {}, h = {
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    };
    l.click = l.mousedown = l.mouseup = l.mousemove = "MouseEvents", t.event = {
        add: o,
        remove: s
    }, t.proxy = function(n, i) {
        if (t.isFunction(n)) {
            var r = function() {
                return n.apply(i, arguments);
            };
            return r._zid = e(n), r;
        }
        if ("string" == typeof i) return t.proxy(n[i], n);
        throw new TypeError("expected function");
    }, t.fn.bind = function(t, e) {
        return this.each(function() {
            o(this, t, e);
        });
    }, t.fn.unbind = function(t, e) {
        return this.each(function() {
            s(this, t, e);
        });
    }, t.fn.one = function(t, e) {
        return this.each(function(n, i) {
            o(this, t, e, null, function(t, e) {
                return function() {
                    var n = t.apply(i, arguments);
                    return s(i, e, t), n;
                };
            });
        });
    };
    var f = function() {
        return !0;
    }, d = function() {
        return !1;
    }, p = /^([A-Z]|layer[XY]$)/, m = {
        preventDefault: "isDefaultPrevented",
        stopImmediatePropagation: "isImmediatePropagationStopped",
        stopPropagation: "isPropagationStopped"
    };
    t.fn.delegate = function(e, n, i) {
        return this.each(function(r, s) {
            o(s, n, i, e, function(n) {
                return function(i) {
                    var r, o = t(i.target).closest(e, s).get(0);
                    return o ? (r = t.extend(a(i), {
                        currentTarget: o,
                        liveFired: s
                    }), n.apply(o, [ r ].concat([].slice.call(arguments, 1)))) : void 0;
                };
            });
        });
    }, t.fn.undelegate = function(t, e, n) {
        return this.each(function() {
            s(this, e, n, t);
        });
    }, t.fn.live = function(e, n) {
        return t(document.body).delegate(this.selector, e, n), this;
    }, t.fn.die = function(e, n) {
        return t(document.body).undelegate(this.selector, e, n), this;
    }, t.fn.on = function(e, n, i) {
        return !n || t.isFunction(n) ? this.bind(e, n || i) : this.delegate(n, e, i);
    }, t.fn.off = function(e, n, i) {
        return !n || t.isFunction(n) ? this.unbind(e, n || i) : this.undelegate(n, e, i);
    }, t.fn.trigger = function(e, n) {
        ("string" == typeof e || t.isPlainObject(e)) && (e = t.Event(e));
        var i = e;
        if (!("defaultPrevented" in i)) {
            i.defaultPrevented = !1;
            var r = i.preventDefault;
            i.preventDefault = function() {
                this.defaultPrevented = !0, r.call(this);
            };
        }
        return e.data = n, this.each(function() {
            "dispatchEvent" in this && this.dispatchEvent(e);
        });
    }, t.fn.triggerHandler = function(e, i) {
        var r, o;
        return this.each(function(s, u) {
            r = a("string" == typeof e ? t.Event(e) : e), r.data = i, r.target = u, t.each(n(u, e.type || e), function(t, e) {
                return o = e.proxy(r), r.isImmediatePropagationStopped() ? !1 : void 0;
            });
        }), o;
    }, "focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select keydown keypress keyup error".split(" ").forEach(function(e) {
        t.fn[e] = function(t) {
            return t ? this.bind(e, t) : this.trigger(e);
        };
    }), [ "focus", "blur" ].forEach(function(e) {
        t.fn[e] = function(t) {
            return t ? this.bind(e, t) : this.each(function() {
                try {
                    this[e]();
                } catch (t) {}
            }), this;
        };
    }), t.Event = function(t, e) {
        "string" != typeof t && (e = t, t = e.type);
        var n = document.createEvent(l[t] || "Events"), i = !0;
        if (e) for (var r in e) "bubbles" == r ? i = !!e[r] : n[r] = e[r];
        return n.initEvent(t, i, !0, null, null, null, null, null, null, null, null, null, null, null, null),
            n.isDefaultPrevented = function() {
                return this.defaultPrevented;
            }, n;
    };
}(Zepto), function(t, e) {
    function n(t) {
        return t.toLowerCase();
    }
    var i, r, o, s, a, u, c, l, h = "", f = window.document.createElement("div"), d = /^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i, p = {};
    t.each({
        Webkit: "webkit",
        Moz: "",
        O: "o",
        ms: "MS"
    }, function(t, r) {
        return f.style[t + "TransitionProperty"] !== e ? (h = "-" + n(t) + "-", i = r, !1) : void 0;
    }), r = h + "transform", p[o = h + "transition-property"] = p[s = h + "transition-duration"] = p[a = h + "transition-timing-function"] = p[u = h + "animation-name"] = p[c = h + "animation-duration"] = p[l = h + "animation-timing-function"] = "",
        t.fx = {
            off: i === e && f.style.transitionProperty === e,
            speeds: {
                _default: 400,
                fast: 200,
                slow: 600
            },
            cssPrefix: h,
            transitionEnd: i ? i + "TransitionEnd" : n("TransitionEnd"),
            animationEnd: i ? i + "AnimationEnd" : n("AnimationEnd")
        }, t.fn.animate = function(e, n, i, r) {
        return t.isObject(n) && (i = n.easing, r = n.complete, n = n.duration), n && (n = ("number" == typeof n ? n : t.fx.speeds[n] || t.fx.speeds._default) / 1e3),
            this.anim(e, n, i, r);
    }, t.fn.anim = function(i, h, f, m) {
        var g, v, y, b = {}, x = "", w = this, _ = t.fx.transitionEnd;
        if (h === e && (h = .4), t.fx.off && (h = 0), "string" == typeof i) b[u] = i, b[c] = h + "s",
            b[l] = f || "linear", _ = t.fx.animationEnd; else {
            v = [];
            for (g in i) d.test(g) ? x += g + "(" + i[g] + ") " : (b[g] = i[g], v.push(n(g.replace(/([a-z])([A-Z])/, "$1-$2"))));
            x && (b[r] = x, v.push(r)), h > 0 && "object" == typeof i && (b[o] = v.join(", "),
                b[s] = h + "s", b[a] = f || "linear");
        }
        return y = function(e) {
            if ("undefined" != typeof e) {
                if (e.target !== e.currentTarget) return;
                t(e.target).unbind(_, arguments.callee);
            }
            t(this).css(p), m && m.call(this);
        }, h > 0 && this.bind(_, y), this.size() && this.get(0).clientLeft, this.css(b),
        0 >= h && setTimeout(function() {
            w.each(function() {
                y.call(this);
            });
        }, 0), this;
    }, f = null;
}(Zepto), function(t) {
    function e(e, n, i, r) {
        return e.global ? (e = n || d, i = t.Event(i), t(e).trigger(i, r), !i.defaultPrevented) : void 0;
    }
    function n(t, n, i) {
        var o = i.context;
        i.success.call(o, t, "success", n), e(i, o, "ajaxSuccess", [ n, i, t ]), r("success", n, i);
    }
    function i(t, n, i, o) {
        var s = o.context;
        o.error.call(s, i, n, t), e(o, s, "ajaxError", [ i, o, t ]), r(n, i, o);
    }
    function r(n, i, r) {
        var o = r.context;
        r.complete.call(o, i, n), e(r, o, "ajaxComplete", [ i, r ]), r.global && !--t.active && e(r, null, "ajaxStop");
    }
    function o() {}
    function s(t) {
        return t && (t == y ? "html" : t == v ? "json" : m.test(t) ? "script" : g.test(t) && "xml") || "text";
    }
    function a(e) {
        e.processData && f(e.data) && (e.data = t.param(e.data, e.traditional)), !e.data || e.type && "GET" != e.type.toUpperCase() || (e.url = (e.url + "&" + e.data).replace(/[&?]{1,2}/, "?"));
    }
    function u(e, n, i, r) {
        var o = t.isArray(n);
        t.each(n, function(n, s) {
            r && (n = i ? r : r + "[" + (o ? "" : n) + "]"), !r && o ? e.add(s.name, s.value) : (i ? t.isArray(s) : f(s)) ? u(e, s, i, n) : e.add(n, s);
        });
    }
    var c, l, h = 0, f = t.isObject, d = window.document, p = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, m = /^(?:text|application)\/javascript/i, g = /^(?:text|application)\/xml/i, v = "application/json", y = "text/html", b = /^\s*$/;
    t.active = 0, t.ajaxJSONP = function(e) {
        if (!("type" in e)) return t.ajax(e);
        var i, s = "jsonp" + ++h, u = d.createElement("script"), c = {
            abort: function() {
                t(u).remove(), s in window && (window[s] = o), r("abort", c, e);
            }
        };
        return e.error && (u.onerror = function() {
            c.abort(), e.error();
        }), window[s] = function(r) {
            clearTimeout(i), t(u).remove(), delete window[s], n(r, c, e);
        }, a(e), u.src = e.url.replace(/=\?/, "=" + s), t("head").append(u), 0 < e.timeout && (i = setTimeout(function() {
            c.abort(), r("timeout", c, e);
        }, e.timeout)), c;
    }, t.ajaxSettings = {
        type: "GET",
        beforeSend: o,
        success: o,
        error: o,
        complete: o,
        context: null,
        global: !0,
        xhr: function() {
            return new window.XMLHttpRequest();
        },
        accepts: {
            script: "text/javascript, application/javascript",
            json: v,
            xml: "application/xml, text/xml",
            html: y,
            text: "text/plain"
        },
        crossDomain: !1,
        timeout: 0,
        processData: !0
    }, t.ajax = function(r) {
        var u = t.extend({}, r || {});
        for (c in t.ajaxSettings) void 0 === u[c] && (u[c] = t.ajaxSettings[c]);
        u.global && 0 === t.active++ && e(u, null, "ajaxStart"), u.crossDomain || (u.crossDomain = /^([\w-]+:)?\/\/([^\/]+)/.test(u.url) && RegExp.$2 != window.location.host);
        var h = u.dataType;
        if (r = /=\?/.test(u.url), "jsonp" == h || r) return r || (u.url = (u.url + "&callback=?").replace(/[&?]{1,2}/, "?")),
            t.ajaxJSONP(u);
        u.url || (u.url = window.location.toString()), a(u), r = u.accepts[h];
        var f, d = {}, p = /^([\w-]+:)\/\//.test(u.url) ? RegExp.$1 : window.location.protocol, m = t.ajaxSettings.xhr();
        u.crossDomain || (d["X-Requested-With"] = "XMLHttpRequest"), r && (d.Accept = r,
        -1 < r.indexOf(",") && (r = r.split(",", 2)[0]), m.overrideMimeType && m.overrideMimeType(r)),
        (u.contentType || !1 !== u.contentType && u.data && "GET" != u.type.toUpperCase()) && (d["Content-Type"] = u.contentType || "application/x-www-form-urlencoded"),
            u.headers = t.extend(d, u.headers || {}), m.onreadystatechange = function() {
            if (4 == m.readyState) {
                clearTimeout(f);
                var e, r = !1;
                if (200 <= m.status && 300 > m.status || 304 == m.status || 0 == m.status && "file:" == p) {
                    h = h || s(m.getResponseHeader("content-type")), e = m.responseText;
                    try {
                        "script" == h ? (0, eval)(e) : "xml" == h ? e = m.responseXML : "json" == h && (e = b.test(e) ? null : t.parseJSON(e));
                    } catch (o) {
                        r = o;
                    }
                    r ? i(r, "parsererror", m, u) : n(e, m, u);
                } else i(null, "error", m, u);
            }
        }, m.open(u.type, u.url, "async" in u ? u.async : !0);
        for (l in u.headers) m.setRequestHeader(l, u.headers[l]);
        return r = u.context, !1 === u.beforeSend.call(r, m, u) || !1 === e(u, r, "ajaxBeforeSend", [ m, u ]) ? r = !1 : (e(u, r, "ajaxSend", [ m, u ]),
            r = void 0), !1 === r ? (m.abort(), !1) : (0 < u.timeout && (f = setTimeout(function() {
            m.onreadystatechange = o, m.abort(), i(null, "timeout", m, u);
        }, u.timeout)), m.send(u.data ? u.data : null), m);
    }, t.get = function(e, n) {
        return t.ajax({
            url: e,
            success: n
        });
    }, t.post = function(e, n, i, r) {
        return t.isFunction(n) && (r = r || i, i = n, n = null), t.ajax({
            type: "POST",
            url: e,
            data: n,
            success: i,
            dataType: r
        });
    }, t.getJSON = function(e, n) {
        return t.ajax({
            url: e,
            success: n,
            dataType: "json"
        });
    }, t.fn.load = function(e, n) {
        if (!this.length) return this;
        var i, r = this, o = e.split(/\s/);
        return 1 < o.length && (e = o[0], i = o[1]), t.get(e, function(e) {
            r.html(i ? t("<div>").html(e.replace(p, "")).find(i) : e), n && n.apply(r, arguments);
        }), this;
    };
    var x = encodeURIComponent;
    t.param = function(t, e) {
        var n = [];
        return n.add = function(t, e) {
            this.push(x(t) + "=" + x(e));
        }, u(n, t, e), n.join("&").replace(/%20/g, "+");
    };
}(Zepto), function(t) {
    function e(e, a) {
        var u = e[s], u = u && i[u];
        if (void 0 === a) return u || n(e);
        if (u) {
            if (a in u) return u[a];
            var c = o(a);
            if (c in u) return u[c];
        }
        return r.call(t(e), a);
    }
    function n(e, n, r) {
        var a, u = e[s] || (e[s] = ++t.uuid);
        if (!(a = i[u])) {
            a = i;
            var c = {};
            t.each(e.attributes, function(e, n) {
                0 == n.name.indexOf("data-") && (c[o(n.name.replace("data-", ""))] = t.zepto.deserializeValue(n.value));
            }), a = a[u] = c;
        }
        return e = a, void 0 !== n && (e[o(n)] = r), e;
    }
    var i = {}, r = t.fn.data, o = t.camelCase, s = t.expando = "Zepto" + +new Date();
    t.fn.data = function(i, r) {
        return void 0 === r ? t.isPlainObject(i) ? this.each(function(e, r) {
            t.each(i, function(t, e) {
                n(r, t, e);
            });
        }) : 0 == this.length ? void 0 : e(this[0], i) : this.each(function() {
            n(this, i, r);
        });
    }, t.fn.removeData = function(e) {
        return "string" == typeof e && (e = e.split(/\s+/)), this.each(function() {
            var n = this[s], r = n && i[n];
            r && t.each(e, function() {
                delete r[o(this)];
            });
        });
    };
}(Zepto), function(t, e) {
    function n(n, i, r, o, s) {
        return "function" == typeof i && !s && (s = i, i = e), r = {
            opacity: r
        }, o && (r.scale = o, n.css(t.fx.cssPrefix + "transform-origin", "0 0")), n.animate(r, i, null, s);
    }
    function i(e, i, r, s) {
        return n(e, i, 0, r, function() {
            o.call(t(this)), s && s.call(this);
        });
    }
    var r = t.fn.show, o = t.fn.hide, s = t.fn.toggle;
    t.fn.show = function(t, i) {
        return r.call(this), t === e ? t = 0 : this.css("opacity", 0), n(this, t, 1, "1,1", i);
    }, t.fn.hide = function(t, n) {
        return t === e ? o.call(this) : i(this, t, "0,0", n);
    }, t.fn.toggle = function(n, i) {
        return n === e || "boolean" == typeof n ? s.call(this, n) : this.each(function() {
            var e = t(this);
            e["none" == e.css("display") ? "show" : "hide"](n, i);
        });
    }, t.fn.fadeTo = function(t, e, i) {
        return n(this, t, e, null, i);
    }, t.fn.fadeIn = function(t, e) {
        var n = this.css("opacity");
        return n > 0 ? this.css("opacity", 0) : n = 1, r.call(this).fadeTo(t, n, e);
    }, t.fn.fadeOut = function(t, e) {
        return i(this, t, null, e);
    }, t.fn.fadeToggle = function(e, n) {
        return this.each(function() {
            var i = t(this);
            i[0 == i.css("opacity") || "none" == i.css("display") ? "fadeIn" : "fadeOut"](e, n);
        });
    };
}(Zepto), !function(t) {
    var e = function(t) {
        this.messages = {
            defaultMessage: "这个值无效。",
            type: {
                email: "这需要是一个有效的电子邮件。",
                url: "这需要是一个有效的网址。",
                urlstrict: "这需要是一个有效的网址。",
                number: "此值应为有效数字。",
                digits: "这个值应该是数字。",
                dateIso: "这个值应该是一个有效的日期（YYYY-MM-DD）。",
                alphanum: "这个值应该是字母。",
                phone: "这个值应该是有效的电话号码。"
            },
            notnull: "这个值不应该是空的。",
            notblank: "这个值不应该是空白。",
            required: "此值是必需的。",
            regexp: "这个值无效。",
            min: "此值应大于或等于%。",
            max: "此值应小于或等于%。",
            range: "此值应为%和%之间。",
            minlength: "这个值太短了。它应该有%的字符或更多。",
            maxlength: "这个值太长。它应该有%的字符或更少。",
            rangelength: "此值长度无效。它应该是%和%的字符长。",
            mincheck: "您必须选择至少%的选择。",
            maxcheck: "您必须选择%的选择或更少。",
            rangecheck: "你必须在%和%的选择中选择。",
            equalto: "这个值应该是相同的。"
        }, this.init(t);
    };
    e.prototype = {
        constructor: e,
        validators: {
            notnull: function(t) {
                return 0 < t.length;
            },
            notblank: function(t) {
                return null !== t && "" !== t.replace(/^\s+/g, "").replace(/\s+$/g, "");
            },
            required: function(t) {
                if ("object" == typeof t) {
                    for (var e in t) if (this.required(t[e])) return !0;
                    return !1;
                }
                return this.notnull(t) && this.notblank(t);
            },
            type: function(t, e) {
                var n;
                switch (e) {
                    case "number":
                        n = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/;
                        break;

                    case "digits":
                        n = /^\d+$/;
                        break;

                    case "alphanum":
                        n = /^\w+$/;
                        break;

                    case "email":
                        n = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
                        break;

                    case "url":
                        t = /(https?|s?ftp|git)/i.test(t) ? t : "http://" + t;

                    case "urlstrict":
                        n = /^(https?|s?ftp|git):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
                        break;

                    case "dateIso":
                        n = /^(\d{4})\D?(0[1-9]|1[0-2])\D?([12]\d|0[1-9]|3[01])$/;
                        break;

                    case "phone":
                        n = /^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$/;
                        break;

                    default:
                        return !1;
                }
                return "" !== t ? n.test(t) : !1;
            },
            regexp: function(t, e, n) {
                return RegExp(e, n.options.regexpFlag || "").test(t);
            },
            minlength: function(t, e) {
                return t.length >= e;
            },
            maxlength: function(t, e) {
                return t.length <= e;
            },
            rangelength: function(t, e) {
                return this.minlength(t, e[0]) && this.maxlength(t, e[1]);
            },
            min: function(t, e) {
                return Number(t) >= e;
            },
            max: function(t, e) {
                return Number(t) <= e;
            },
            range: function(t, e) {
                return t >= e[0] && t <= e[1];
            },
            equalto: function(e, n, i) {
                return i.options.validateIfUnchanged = !0, e === t(n).val();
            },
            remote: function(e, n, i) {
                var r = {}, o = {};
                r[i.$element.attr("name")] = e, "undefined" != typeof i.options.remoteDatatype && (o = {
                    dataType: i.options.remoteDatatype
                });
                var s = function(e, n) {
                    "undefined" != typeof n && "undefined" != typeof i.Validator.messages.remote && n !== i.Validator.messages.remote && t(i.ulError + " .remote").remove(),
                        i.updtConstraint({
                            name: "remote",
                            valid: e
                        }, n), i.manageValidationResult();
                }, a = function(e) {
                    if ("object" == typeof e) return e;
                    try {
                        e = t.parseJSON(e);
                    } catch (n) {}
                    return e;
                }, u = function(t) {
                    return "object" == typeof t && null !== t ? "undefined" != typeof t.error ? t.error : "undefined" != typeof t.message ? t.message : null : null;
                };
                return t.ajax(t.extend({}, {
                    url: n,
                    data: r,
                    type: i.options.remoteMethod || "GET",
                    success: function(t) {
                        t = a(t), s(1 === t || !0 === t || "object" == typeof t && null !== t && "undefined" != typeof t.success, u(t));
                    },
                    error: function(t) {
                        t = a(t), s(!1, u(t));
                    }
                }, o)), null;
            },
            mincheck: function(t, e) {
                return this.minlength(t, e);
            },
            maxcheck: function(t, e) {
                return this.maxlength(t, e);
            },
            rangecheck: function(t, e) {
                return this.rangelength(t, e);
            }
        },
        init: function(t) {
            var e = t.validators;
            t = t.messages;
            for (var n in e) this.addValidator(n, e[n]);
            for (n in t) this.addMessage(n, t[n]);
        },
        formatMesssage: function(t, e) {
            if ("object" == typeof e) {
                for (var n in e) t = this.formatMesssage(t, e[n]);
                return t;
            }
            return "string" == typeof t ? t.replace(/%s/i, e) : "";
        },
        addValidator: function(t, e) {
            this.validators[t] = e;
        },
        addMessage: function(t, e, n) {
            if ("undefined" != typeof n && !0 === n) this.messages.type[t] = e; else if ("type" === t) for (var i in e) this.messages.type[i] = e[i]; else this.messages[t] = e;
        }
    };
    var n = function(t, n, i) {
        return this.options = n, this.Validator = new e(n), "ParsleyFieldMultiple" === i ? this : void this.init(t, i || "ParsleyField");
    };
    n.prototype = {
        constructor: n,
        init: function(e, n) {
            this.type = n, this.valid = !0, this.element = e, this.validatedOnce = !1, this.$element = t(e),
                this.val = this.$element.val(), this.isRequired = !1, this.constraints = {}, "undefined" == typeof this.isRadioOrCheckbox && (this.isRadioOrCheckbox = !1,
                this.hash = this.generateHash(), this.errorClassHandler = this.options.errors.classHandler(e, this.isRadioOrCheckbox) || this.$element),
                this.ulErrorManagement(), this.bindHtml5Constraints(), this.addConstraints(), this.hasConstraints() && this.bindValidationEvents();
        },
        setParent: function(e) {
            this.$parent = t(e);
        },
        getParent: function() {
            return this.$parent;
        },
        bindHtml5Constraints: function() {
            (this.$element.hasClass("required") || this.$element.attr("required")) && (this.options.required = !0),
            "undefined" != typeof this.$element.attr("type") && RegExp(this.$element.attr("type"), "i").test("email url number range") && (this.options.type = this.$element.attr("type"),
            RegExp(this.options.type, "i").test("number range") && (this.options.type = "number",
            "undefined" != typeof this.$element.attr("min") && this.$element.attr("min").length && (this.options.min = this.$element.attr("min")),
            "undefined" != typeof this.$element.attr("max") && this.$element.attr("max").length && (this.options.max = this.$element.attr("max")))),
            "string" == typeof this.$element.attr("pattern") && this.$element.attr("pattern").length && (this.options.regexp = this.$element.attr("pattern"));
        },
        addConstraints: function() {
            for (var t in this.options) {
                var e = {};
                e[t] = this.options[t], this.addConstraint(e, !0);
            }
        },
        addConstraint: function(t, e) {
            for (var n in t) n = n.toLowerCase(), "function" == typeof this.Validator.validators[n] && (this.constraints[n] = {
                name: n,
                requirements: t[n],
                valid: null
            }, "required" === n && (this.isRequired = !0), this.addCustomConstraintMessage(n));
            "undefined" == typeof e && this.bindValidationEvents();
        },
        updateConstraint: function(t, e) {
            for (var n in t) this.updtConstraint({
                name: n,
                requirements: t[n],
                valid: null
            }, e);
        },
        updtConstraint: function(e, n) {
            this.constraints[e.name] = t.extend(!0, this.constraints[e.name], e), "string" == typeof n && (this.Validator.messages[e.name] = n),
                this.bindValidationEvents();
        },
        removeConstraint: function(t) {
            t = t.toLowerCase(), delete this.constraints[t], "required" === t && (this.isRequired = !1),
                this.hasConstraints() ? this.bindValidationEvents() : "ParsleyForm" == typeof this.getParent() ? this.getParent().removeItem(this.$element) : this.destroy();
        },
        addCustomConstraintMessage: function(t) {
            var e = t + ("type" === t && "undefined" != typeof this.options[t] ? this.options[t].charAt(0).toUpperCase() + this.options[t].substr(1) : "") + "Message";
            "undefined" != typeof this.options[e] && this.Validator.addMessage("type" === t ? this.options[t] : t, this.options[e], "type" === t);
        },
        bindValidationEvents: function() {
            this.valid = null, this.$element.addClass("parsley-validated"), this.$element.off("." + this.type),
            this.options.remote && !/change/i.test(this.options.trigger) && (this.options.trigger = this.options.trigger ? " change" : "change");
            var e = (this.options.trigger ? this.options.trigger : "") + (/key/i.test(this.options.trigger) ? "" : " keyup");
            this.$element.is("select") && (e += /change/i.test(e) ? "" : " change"), e = e.replace(/^\s+/g, "").replace(/\s+$/g, ""),
                this.$element.on((e + " ").split(" ").join("." + this.type + " "), !1, t.proxy(this.eventValidation, this));
        },
        generateHash: function() {
            return "parsley-" + (Math.random() + "").substring(2);
        },
        getHash: function() {
            return this.hash;
        },
        getVal: function() {
            return this.$element.data("value") || this.$element.val();
        },
        eventValidation: function(t) {
            var e = this.getVal();
            return "keyup" === t.type && !/keyup/i.test(this.options.trigger) && !this.validatedOnce || "change" === t.type && !/change/i.test(this.options.trigger) && !this.validatedOnce || !this.isRadioOrCheckbox && e.length < this.options.validationMinlength && !this.validatedOnce ? !0 : void this.validate();
        },
        isValid: function() {
            return this.validate(!1);
        },
        hasConstraints: function() {
            for (var t in this.constraints) return !0;
            return !1;
        },
        validate: function(t) {
            var e = this.getVal(), n = null;
            return this.hasConstraints() ? this.options.listeners.onFieldValidate(this.element, this) || "" === e && !this.isRequired ? (this.reset(),
                null) : this.needsValidation(e) ? (n = this.applyValidators(), ("undefined" != typeof t ? t : this.options.showErrors) && this.manageValidationResult(),
                n) : this.valid : null;
        },
        needsValidation: function(t) {
            return !this.options.validateIfUnchanged && null !== this.valid && this.val === t && this.validatedOnce ? !1 : (this.val = t,
                this.validatedOnce = !0);
        },
        applyValidators: function() {
            var t, e = null;
            for (t in this.constraints) {
                var n = this.Validator.validators[this.constraints[t].name](this.val, this.constraints[t].requirements, this);
                !1 === n ? (e = !1, this.constraints[t].valid = e, this.options.listeners.onFieldError(this.element, this.constraints, this)) : !0 === n && (this.constraints[t].valid = !0,
                    e = !1 !== e, this.options.listeners.onFieldSuccess(this.element, this.constraints, this));
            }
            return e;
        },
        manageValidationResult: function() {
            var t, e = null;
            for (t in this.constraints) !1 === this.constraints[t].valid ? (this.manageError(this.constraints[t]),
                e = !1) : !0 === this.constraints[t].valid && (this.removeError(this.constraints[t].name),
                e = !1 !== e);
            return this.valid = e, !0 === this.valid ? (this.removeErrors(), this.errorClassHandler.removeClass(this.options.errorClass).addClass(this.options.successClass),
                !0) : !1 === this.valid ? (this.errorClassHandler.removeClass(this.options.successClass).addClass(this.options.errorClass),
                !1) : e;
        },
        ulErrorManagement: function() {
            this.ulError = "#" + this.hash, this.ulTemplate = t(this.options.errors.errorsWrapper).attr("id", this.hash).addClass("parsley-error-list");
        },
        removeError: function(e) {
            e = this.ulError + " ." + e;
            var n = this;
            this.options.animate ? t(e).fadeOut(this.options.animateDuration, function() {
                t(this).remove(), n.ulError && 0 === t(n.ulError).children().length && n.removeErrors();
            }) : t(e).remove(), this.ulError && 0 === t(this.ulError).children().length && this.removeErrors();
        },
        addError: function(e) {
            for (var n in e) {
                var i = t(this.options.errors.errorElem).addClass(n);
                t(this.ulError).append(this.options.animate ? t(i).html(e[n]).hide().fadeIn(this.options.animateDuration) : t(i).html(e[n]));
            }
        },
        removeErrors: function() {
            this.options.animate ? t(this.ulError).fadeOut(this.options.animateDuration, function() {
                t(this).remove();
            }) : t(this.ulError).remove();
        },
        reset: function() {
            this.valid = null, this.removeErrors(), this.validatedOnce = !1, this.errorClassHandler.removeClass(this.options.successClass).removeClass(this.options.errorClass);
            for (var t in this.constraints) this.constraints[t].valid = null;
            return this;
        },
        manageError: function(e) {
            if (t(this.ulError).length || this.manageErrorContainer(), !("required" === e.name && null !== this.getVal() && 0 < this.getVal().length || this.isRequired && "required" !== e.name && (null === this.getVal() || 0 === this.getVal().length))) {
                var n = e.name, i = !1 !== this.options.errorMessage ? "custom-error-message" : n, r = {};
                e = !1 !== this.options.errorMessage ? this.options.errorMessage : "type" === e.name ? this.Validator.messages[n][e.requirements] : "undefined" == typeof this.Validator.messages[n] ? this.Validator.messages.defaultMessage : this.Validator.formatMesssage(this.Validator.messages[n], e.requirements),
                t(this.ulError + " ." + i).length || (r[i] = e, this.addError(r));
            }
        },
        manageErrorContainer: function() {
            var e = this.options.errorContainer || this.options.errors.container(this.element, this.isRadioOrCheckbox), n = this.options.animate ? this.ulTemplate.show() : this.ulTemplate;
            "undefined" != typeof e ? t(e).append(n) : this.isRadioOrCheckbox ? this.$element.parent().after(n) : this.$element.after(n);
        },
        addListener: function(t) {
            for (var e in t) this.options.listeners[e] = t[e];
        },
        destroy: function() {
            this.$element.removeClass("parsley-validated"), this.reset().$element.off("." + this.type).removeData(this.type);
        }
    };
    var i = function(t, n, i) {
        this.initMultiple(t, n), this.inherit(t, n), this.Validator = new e(n), this.init(t, i || "ParsleyFieldMultiple");
    };
    i.prototype = {
        constructor: i,
        initMultiple: function(e, n) {
            this.element = e, this.$element = t(e), this.group = n.group || !1, this.hash = this.getName(),
                this.siblings = this.group ? '[data-group="' + this.group + '"]' : 'input[name="' + this.$element.attr("name") + '"]',
                this.isRadioOrCheckbox = !0, this.isRadio = this.$element.is("input[type=radio]"),
                this.isCheckbox = this.$element.is("input[type=checkbox]"), this.errorClassHandler = n.errors.classHandler(e, this.isRadioOrCheckbox) || this.$element.parent();
        },
        inherit: function(t, e) {
            var i, r = new n(t, e, "ParsleyFieldMultiple");
            for (i in r) "undefined" == typeof this[i] && (this[i] = r[i]);
        },
        getName: function() {
            if (this.group) return "parsley-" + this.group;
            if ("undefined" == typeof this.$element.attr("name")) throw "A radio / checkbox input must have a data-group attribute or a name to be Parsley validated !";
            return "parsley-" + this.$element.attr("name").replace(/(:|\.|\[|\])/g, "");
        },
        getVal: function() {
            if (this.isRadio) return t(this.siblings + ":checked").val() || "";
            if (this.isCheckbox) {
                var e = [];
                return t(this.siblings + ":checked").each(function() {
                    e.push(t(this).val());
                }), e;
            }
        },
        bindValidationEvents: function() {
            this.valid = null, this.$element.addClass("parsley-validated"), this.$element.off("." + this.type);
            var e = this, n = (this.options.trigger ? this.options.trigger : "") + (/change/i.test(this.options.trigger) ? "" : " change"), n = n.replace(/^\s+/g, "").replace(/\s+$/g, "");
            t(this.siblings).each(function() {
                t(this).on(n.split(" ").join("." + e.type + " "), !1, t.proxy(e.eventValidation, e));
            });
        }
    };
    var r = function(t, e, n) {
        this.init(t, e, n || "parsleyForm");
    };
    r.prototype = {
        constructor: r,
        init: function(e, n, i) {
            this.type = i, this.items = [], this.$element = t(e), this.options = n;
            var r = this;
            this.$element.find(n.inputs).each(function() {
                r.addItem(this);
            }), this.$element.on("submit." + this.type, !1, t.proxy(this.validate, this));
        },
        addListener: function(t) {
            for (var e in t) if (/Field/.test(e)) for (var n = 0; n < this.items.length; n++) this.items[n].addListener(t); else this.options.listeners[e] = t[e];
        },
        addItem: function(e) {
            return t(e).is(this.options.excluded) ? !1 : (e = t(e).parsley(this.options), e.setParent(this),
                void this.items.push(e));
        },
        removeItem: function(e) {
            e = t(e).parsley();
            for (var n = 0; n < this.items.length; n++) if (this.items[n].hash === e.hash) return this.items[n].destroy(),
                this.items.splice(n, 1), !0;
            return !1;
        },
        validate: function(t) {
            var e = !0;
            this.focusedField = !1;
            for (var n = 0; n < this.items.length; n++) "undefined" != typeof this.items[n] && !1 === this.items[n].validate() && (e = !1,
            !this.focusedField && "first" === this.options.focus || "last" === this.options.focus) && (this.focusedField = this.items[n].$element);
            return this.focusedField && !e && this.focusedField.focus(), this.options.listeners.onFormSubmit(e, t, this),
                e;
        },
        isValid: function() {
            for (var t = 0; t < this.items.length; t++) if (!1 === this.items[t].isValid()) return !1;
            return !0;
        },
        removeErrors: function() {
            for (var t = 0; t < this.items.length; t++) this.items[t].parsley("reset");
        },
        destroy: function() {
            for (var t = 0; t < this.items.length; t++) this.items[t].destroy();
            this.$element.off("." + this.type).removeData(this.type);
        },
        reset: function() {
            for (var t = 0; t < this.items.length; t++) this.items[t].reset();
        }
    }, t.fn.parsley = function(e, o) {
        function s(s, u) {
            var c = t(s).data(u);
            if (!c) {
                switch (u) {
                    case "parsleyForm":
                        c = new r(s, a, "parsleyForm");
                        break;

                    case "parsleyField":
                        c = new n(s, a, "parsleyField");
                        break;

                    case "parsleyFieldMultiple":
                        c = new i(s, a, "parsleyFieldMultiple");
                        break;

                    default:
                        return;
                }
                t(s).data(u, c);
            }
            return "string" == typeof e && "function" == typeof c[e] ? (c = c[e](o), "undefined" != typeof c ? c : t(s)) : c;
        }
        var a = t.extend(!0, {}, t.fn.parsley.defaults, "undefined" != typeof window.ParsleyConfig ? window.ParsleyConfig : {}, e, this.data()), u = null;
        return t(this).is("form") ? u = s(t(this), "parsleyForm") : t(this).is(a.inputs) && !t(this).is(a.excluded) && (u = s(t(this), t(this).is("input[type=radio], input[type=checkbox]") ? "parsleyFieldMultiple" : "parsleyField")),
            "function" == typeof o ? o() : u;
    }, t.fn.parsley.Constructor = r, t.fn.parsley.defaults = {
        inputs: "input, textarea, select",
        excluded: "input[type=hidden], :disabled",
        trigger: !1,
        animate: !0,
        animateDuration: 300,
        focus: "first",
        validationMinlength: 3,
        successClass: "parsley-success",
        errorClass: "parsley-error",
        errorMessage: !1,
        validators: {},
        showErrors: !0,
        messages: {},
        validateIfUnchanged: !1,
        errors: {
            classHandler: function() {},
            container: function() {},
            errorsWrapper: "<ul></ul>",
            errorElem: "<li></li>"
        },
        listeners: {
            onFieldValidate: function() {
                return !1;
            },
            onFormSubmit: function() {},
            onFieldError: function() {},
            onFieldSuccess: function() {}
        }
    }, t(window).on("load", function() {
        t('[data-validate="parsley"]').each(function() {
            t(this).parsley();
        });
    });
}(window.jQuery || window.Zepto), function() {
    function t(e, n, i) {
        if (e === n) return 0 !== e || 1 / e == 1 / n;
        if (null == e || null == n) return e === n;
        if (e._chain && (e = e._wrapped), n._chain && (n = n._wrapped), e.isEqual && w.isFunction(e.isEqual)) return e.isEqual(n);
        if (n.isEqual && w.isFunction(n.isEqual)) return n.isEqual(e);
        var r = u.call(e);
        if (r != u.call(n)) return !1;
        switch (r) {
            case "[object String]":
                return e == String(n);

            case "[object Number]":
                return e != +e ? n != +n : 0 == e ? 1 / e == 1 / n : e == +n;

            case "[object Date]":
            case "[object Boolean]":
                return +e == +n;

            case "[object RegExp]":
                return e.source == n.source && e.global == n.global && e.multiline == n.multiline && e.ignoreCase == n.ignoreCase;
        }
        if ("object" != typeof e || "object" != typeof n) return !1;
        for (var o = i.length; o--; ) if (i[o] == e) return !0;
        i.push(e);
        var o = 0, s = !0;
        if ("[object Array]" == r) {
            if (o = e.length, s = o == n.length) for (;o-- && (s = o in e == o in n && t(e[o], n[o], i)); ) ;
        } else {
            if ("constructor" in e != "constructor" in n || e.constructor != n.constructor) return !1;
            for (var a in e) if (w.has(e, a) && (o++, !(s = w.has(n, a) && t(e[a], n[a], i)))) break;
            if (s) {
                for (a in n) if (w.has(n, a) && !o--) break;
                s = !o;
            }
        }
        return i.pop(), s;
    }
    var e = this, n = e._, i = {}, r = Array.prototype, o = Object.prototype, s = r.slice, a = r.unshift, u = o.toString, c = o.hasOwnProperty, l = r.forEach, h = r.map, f = r.reduce, d = r.reduceRight, p = r.filter, m = r.every, g = r.some, v = r.indexOf, y = r.lastIndexOf, o = Array.isArray, b = Object.keys, x = Function.prototype.bind, w = function(t) {
        return new j(t);
    };
    "undefined" != typeof exports ? ("undefined" != typeof module && module.exports && (exports = module.exports = w),
        exports._ = w) : e._ = w, w.VERSION = "1.3.3";
    var _ = w.each = w.forEach = function(t, e, n) {
        if (null != t) if (l && t.forEach === l) t.forEach(e, n); else if (t.length === +t.length) for (var r = 0, o = t.length; o > r && !(r in t && e.call(n, t[r], r, t) === i); r++) ; else for (r in t) if (w.has(t, r) && e.call(n, t[r], r, t) === i) break;
    };
    w.map = w.collect = function(t, e, n) {
        var i = [];
        return null == t ? i : h && t.map === h ? t.map(e, n) : (_(t, function(t, r, o) {
            i[i.length] = e.call(n, t, r, o);
        }), t.length === +t.length && (i.length = t.length), i);
    }, w.reduce = w.foldl = w.inject = function(t, e, n, i) {
        var r = 2 < arguments.length;
        if (null == t && (t = []), f && t.reduce === f) return i && (e = w.bind(e, i)),
            r ? t.reduce(e, n) : t.reduce(e);
        if (_(t, function(t, o, s) {
                r ? n = e.call(i, n, t, o, s) : (n = t, r = !0);
            }), !r) throw new TypeError("Reduce of empty array with no initial value");
        return n;
    }, w.reduceRight = w.foldr = function(t, e, n, i) {
        var r = 2 < arguments.length;
        if (null == t && (t = []), d && t.reduceRight === d) return i && (e = w.bind(e, i)),
            r ? t.reduceRight(e, n) : t.reduceRight(e);
        var o = w.toArray(t).reverse();
        return i && !r && (e = w.bind(e, i)), r ? w.reduce(o, e, n, i) : w.reduce(o, e);
    }, w.find = w.detect = function(t, e, n) {
        var i;
        return k(t, function(t, r, o) {
            return e.call(n, t, r, o) ? (i = t, !0) : void 0;
        }), i;
    }, w.filter = w.select = function(t, e, n) {
        var i = [];
        return null == t ? i : p && t.filter === p ? t.filter(e, n) : (_(t, function(t, r, o) {
            e.call(n, t, r, o) && (i[i.length] = t);
        }), i);
    }, w.reject = function(t, e, n) {
        var i = [];
        return null == t ? i : (_(t, function(t, r, o) {
            e.call(n, t, r, o) || (i[i.length] = t);
        }), i);
    }, w.every = w.all = function(t, e, n) {
        var r = !0;
        return null == t ? r : m && t.every === m ? t.every(e, n) : (_(t, function(t, o, s) {
            return (r = r && e.call(n, t, o, s)) ? void 0 : i;
        }), !!r);
    };
    var k = w.some = w.any = function(t, e, n) {
        e || (e = w.identity);
        var r = !1;
        return null == t ? r : g && t.some === g ? t.some(e, n) : (_(t, function(t, o, s) {
            return r || (r = e.call(n, t, o, s)) ? i : void 0;
        }), !!r);
    };
    w.include = w.contains = function(t, e) {
        var n = !1;
        return null == t ? n : v && t.indexOf === v ? -1 != t.indexOf(e) : n = k(t, function(t) {
            return t === e;
        });
    }, w.invoke = function(t, e) {
        var n = s.call(arguments, 2);
        return w.map(t, function(t) {
            return (w.isFunction(e) ? e || t : t[e]).apply(t, n);
        });
    }, w.pluck = function(t, e) {
        return w.map(t, function(t) {
            return t[e];
        });
    }, w.max = function(t, e, n) {
        if (!e && w.isArray(t) && t[0] === +t[0]) return Math.max.apply(Math, t);
        if (!e && w.isEmpty(t)) return -(1 / 0);
        var i = {
            computed: -(1 / 0)
        };
        return _(t, function(t, r, o) {
            r = e ? e.call(n, t, r, o) : t, r >= i.computed && (i = {
                value: t,
                computed: r
            });
        }), i.value;
    }, w.min = function(t, e, n) {
        if (!e && w.isArray(t) && t[0] === +t[0]) return Math.min.apply(Math, t);
        if (!e && w.isEmpty(t)) return 1 / 0;
        var i = {
            computed: 1 / 0
        };
        return _(t, function(t, r, o) {
            r = e ? e.call(n, t, r, o) : t, r < i.computed && (i = {
                value: t,
                computed: r
            });
        }), i.value;
    }, w.shuffle = function(t) {
        var e, n = [];
        return _(t, function(t, i) {
            e = Math.floor(Math.random() * (i + 1)), n[i] = n[e], n[e] = t;
        }), n;
    }, w.sortBy = function(t, e, n) {
        var i = w.isFunction(e) ? e : function(t) {
            return t[e];
        };
        return w.pluck(w.map(t, function(t, e, r) {
            return {
                value: t,
                criteria: i.call(n, t, e, r)
            };
        }).sort(function(t, e) {
            var n = t.criteria, i = e.criteria;
            return void 0 === n ? 1 : void 0 === i ? -1 : i > n ? -1 : n > i ? 1 : 0;
        }), "value");
    }, w.groupBy = function(t, e) {
        var n = {}, i = w.isFunction(e) ? e : function(t) {
            return t[e];
        };
        return _(t, function(t, e) {
            var r = i(t, e);
            (n[r] || (n[r] = [])).push(t);
        }), n;
    }, w.sortedIndex = function(t, e, n) {
        n || (n = w.identity);
        for (var i = 0, r = t.length; r > i; ) {
            var o = i + r >> 1;
            n(t[o]) < n(e) ? i = o + 1 : r = o;
        }
        return i;
    }, w.toArray = function(t) {
        return t ? w.isArray(t) || w.isArguments(t) ? s.call(t) : t.toArray && w.isFunction(t.toArray) ? t.toArray() : w.values(t) : [];
    }, w.size = function(t) {
        return w.isArray(t) ? t.length : w.keys(t).length;
    }, w.first = w.head = w.take = function(t, e, n) {
        return null == e || n ? t[0] : s.call(t, 0, e);
    }, w.initial = function(t, e, n) {
        return s.call(t, 0, t.length - (null == e || n ? 1 : e));
    }, w.last = function(t, e, n) {
        return null == e || n ? t[t.length - 1] : s.call(t, Math.max(t.length - e, 0));
    }, w.rest = w.tail = function(t, e, n) {
        return s.call(t, null == e || n ? 1 : e);
    }, w.compact = function(t) {
        return w.filter(t, function(t) {
            return !!t;
        });
    }, w.flatten = function(t, e) {
        return w.reduce(t, function(t, n) {
            return w.isArray(n) ? t.concat(e ? n : w.flatten(n)) : (t[t.length] = n, t);
        }, []);
    }, w.without = function(t) {
        return w.difference(t, s.call(arguments, 1));
    }, w.uniq = w.unique = function(t, e, n) {
        n = n ? w.map(t, n) : t;
        var i = [];
        return 3 > t.length && (e = !0), w.reduce(n, function(n, r, o) {
            return (e ? w.last(n) === r && n.length : w.include(n, r)) || (n.push(r), i.push(t[o])),
                n;
        }, []), i;
    }, w.union = function() {
        return w.uniq(w.flatten(arguments, !0));
    }, w.intersection = w.intersect = function(t) {
        var e = s.call(arguments, 1);
        return w.filter(w.uniq(t), function(t) {
            return w.every(e, function(e) {
                return 0 <= w.indexOf(e, t);
            });
        });
    }, w.difference = function(t) {
        var e = w.flatten(s.call(arguments, 1), !0);
        return w.filter(t, function(t) {
            return !w.include(e, t);
        });
    }, w.zip = function() {
        for (var t = s.call(arguments), e = w.max(w.pluck(t, "length")), n = Array(e), i = 0; e > i; i++) n[i] = w.pluck(t, "" + i);
        return n;
    }, w.indexOf = function(t, e, n) {
        if (null == t) return -1;
        var i;
        if (n) return n = w.sortedIndex(t, e), t[n] === e ? n : -1;
        if (v && t.indexOf === v) return t.indexOf(e);
        for (n = 0, i = t.length; i > n; n++) if (n in t && t[n] === e) return n;
        return -1;
    }, w.lastIndexOf = function(t, e) {
        if (null == t) return -1;
        if (y && t.lastIndexOf === y) return t.lastIndexOf(e);
        for (var n = t.length; n--; ) if (n in t && t[n] === e) return n;
        return -1;
    }, w.range = function(t, e, n) {
        1 >= arguments.length && (e = t || 0, t = 0), n = arguments[2] || 1;
        for (var i = Math.max(Math.ceil((e - t) / n), 0), r = 0, o = Array(i); i > r; ) o[r++] = t,
            t += n;
        return o;
    };
    var C = function() {};
    w.bind = function(t, e) {
        var n, i;
        if (t.bind === x && x) return x.apply(t, s.call(arguments, 1));
        if (!w.isFunction(t)) throw new TypeError();
        return i = s.call(arguments, 2), n = function() {
            if (!(this instanceof n)) return t.apply(e, i.concat(s.call(arguments)));
            C.prototype = t.prototype;
            var r = new C(), o = t.apply(r, i.concat(s.call(arguments)));
            return Object(o) === o ? o : r;
        };
    }, w.bindAll = function(t) {
        var e = s.call(arguments, 1);
        return 0 == e.length && (e = w.functions(t)), _(e, function(e) {
            t[e] = w.bind(t[e], t);
        }), t;
    }, w.memoize = function(t, e) {
        var n = {};
        return e || (e = w.identity), function() {
            var i = e.apply(this, arguments);
            return w.has(n, i) ? n[i] : n[i] = t.apply(this, arguments);
        };
    }, w.delay = function(t, e) {
        var n = s.call(arguments, 2);
        return setTimeout(function() {
            return t.apply(null, n);
        }, e);
    }, w.defer = function(t) {
        return w.delay.apply(w, [ t, 1 ].concat(s.call(arguments, 1)));
    }, w.throttle = function(t, e) {
        var n, i, r, o, s, a, u = w.debounce(function() {
            s = o = !1;
        }, e);
        return function() {
            return n = this, i = arguments, r || (r = setTimeout(function() {
                r = null, s && t.apply(n, i), u();
            }, e)), o ? s = !0 : a = t.apply(n, i), u(), o = !0, a;
        };
    }, w.debounce = function(t, e, n) {
        var i;
        return function() {
            var r = this, o = arguments;
            n && !i && t.apply(r, o), clearTimeout(i), i = setTimeout(function() {
                i = null, n || t.apply(r, o);
            }, e);
        };
    }, w.once = function(t) {
        var e, n = !1;
        return function() {
            return n ? e : (n = !0, e = t.apply(this, arguments));
        };
    }, w.wrap = function(t, e) {
        return function() {
            var n = [ t ].concat(s.call(arguments, 0));
            return e.apply(this, n);
        };
    }, w.compose = function() {
        var t = arguments;
        return function() {
            for (var e = arguments, n = t.length - 1; n >= 0; n--) e = [ t[n].apply(this, e) ];
            return e[0];
        };
    }, w.after = function(t, e) {
        return 0 >= t ? e() : function() {
            return 1 > --t ? e.apply(this, arguments) : void 0;
        };
    }, w.keys = b || function(t) {
            if (t !== Object(t)) throw new TypeError("Invalid object");
            var e, n = [];
            for (e in t) w.has(t, e) && (n[n.length] = e);
            return n;
        }, w.values = function(t) {
        return w.map(t, w.identity);
    }, w.functions = w.methods = function(t) {
        var e, n = [];
        for (e in t) w.isFunction(t[e]) && n.push(e);
        return n.sort();
    }, w.extend = function(t) {
        return _(s.call(arguments, 1), function(e) {
            for (var n in e) t[n] = e[n];
        }), t;
    }, w.pick = function(t) {
        var e = {};
        return _(w.flatten(s.call(arguments, 1)), function(n) {
            n in t && (e[n] = t[n]);
        }), e;
    }, w.defaults = function(t) {
        return _(s.call(arguments, 1), function(e) {
            for (var n in e) null == t[n] && (t[n] = e[n]);
        }), t;
    }, w.clone = function(t) {
        return w.isObject(t) ? w.isArray(t) ? t.slice() : w.extend({}, t) : t;
    }, w.tap = function(t, e) {
        return e(t), t;
    }, w.isEqual = function(e, n) {
        return t(e, n, []);
    }, w.isEmpty = function(t) {
        if (null == t) return !0;
        if (w.isArray(t) || w.isString(t)) return 0 === t.length;
        for (var e in t) if (w.has(t, e)) return !1;
        return !0;
    }, w.isElement = function(t) {
        return !(!t || 1 != t.nodeType);
    }, w.isArray = o || function(t) {
            return "[object Array]" == u.call(t);
        }, w.isObject = function(t) {
        return t === Object(t);
    }, w.isArguments = function(t) {
        return "[object Arguments]" == u.call(t);
    }, w.isArguments(arguments) || (w.isArguments = function(t) {
        return !(!t || !w.has(t, "callee"));
    }), w.isFunction = function(t) {
        return "[object Function]" == u.call(t);
    }, w.isString = function(t) {
        return "[object String]" == u.call(t);
    }, w.isNumber = function(t) {
        return "[object Number]" == u.call(t);
    }, w.isFinite = function(t) {
        return w.isNumber(t) && isFinite(t);
    }, w.isNaN = function(t) {
        return t !== t;
    }, w.isBoolean = function(t) {
        return !0 === t || !1 === t || "[object Boolean]" == u.call(t);
    }, w.isDate = function(t) {
        return "[object Date]" == u.call(t);
    }, w.isRegExp = function(t) {
        return "[object RegExp]" == u.call(t);
    }, w.isNull = function(t) {
        return null === t;
    }, w.isUndefined = function(t) {
        return void 0 === t;
    }, w.has = function(t, e) {
        return c.call(t, e);
    }, w.noConflict = function() {
        return e._ = n, this;
    }, w.identity = function(t) {
        return t;
    }, w.times = function(t, e, n) {
        for (var i = 0; t > i; i++) e.call(n, i);
    }, w.escape = function(t) {
        return ("" + t).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/\//g, "&#x2F;");
    }, w.result = function(t, e) {
        if (null == t) return null;
        var n = t[e];
        return w.isFunction(n) ? n.call(t) : n;
    }, w.mixin = function(t) {
        _(w.functions(t), function(e) {
            var n = w[e] = t[e];
            j.prototype[e] = function() {
                var t = s.call(arguments);
                return a.call(t, this._wrapped), M(n.apply(w, t), this._chain);
            };
        });
    };
    var E = 0;
    w.uniqueId = function(t) {
        var e = E++;
        return t ? t + e : e;
    }, w.templateSettings = {
        evaluate: /<%([\s\S]+?)%>/g,
        interpolate: /<%=([\s\S]+?)%>/g,
        escape: /<%-([\s\S]+?)%>/g
    };
    var T, A = /.^/, N = {
        "\\": "\\",
        "'": "'",
        r: "\r",
        n: "\n",
        t: "	",
        u2028: "\u2028",
        u2029: "\u2029"
    };
    for (T in N) N[N[T]] = T;
    var S = /\\|'|\r|\n|\t|\u2028|\u2029/g, F = /\\(\\|'|r|n|t|u2028|u2029)/g, $ = function(t) {
        return t.replace(F, function(t, e) {
            return N[e];
        });
    };
    w.template = function(t, e, n) {
        n = w.defaults(n || {}, w.templateSettings), t = "__p+='" + t.replace(S, function(t) {
                return "\\" + N[t];
            }).replace(n.escape || A, function(t, e) {
                return "'+\n_.escape(" + $(e) + ")+\n'";
            }).replace(n.interpolate || A, function(t, e) {
                return "'+\n(" + $(e) + ")+\n'";
            }).replace(n.evaluate || A, function(t, e) {
                return "';\n" + $(e) + "\n;__p+='";
            }) + "';\n", n.variable || (t = "with(obj||{}){\n" + t + "}\n"), t = "var __p='';var print=function(){__p+=Array.prototype.join.call(arguments, '')};\n" + t + "return __p;\n";
        var i = new Function(n.variable || "obj", "_", t);
        return e ? i(e, w) : (e = function(t) {
            return i.call(this, t, w);
        }, e.source = "function(" + (n.variable || "obj") + "){\n" + t + "}", e);
    }, w.chain = function(t) {
        return w(t).chain();
    };
    var j = function(t) {
        this._wrapped = t;
    };
    w.prototype = j.prototype;
    var M = function(t, e) {
        return e ? w(t).chain() : t;
    };
    w.mixin(w), _("pop push reverse shift sort splice unshift".split(" "), function(t) {
        var e = r[t];
        j.prototype[t] = function() {
            var n = this._wrapped;
            e.apply(n, arguments);
            var i = n.length;
            return ("shift" == t || "splice" == t) && 0 === i && delete n[0], M(n, this._chain);
        };
    }), _([ "concat", "join", "slice" ], function(t) {
        var e = r[t];
        j.prototype[t] = function() {
            return M(e.apply(this._wrapped, arguments), this._chain);
        };
    }), j.prototype.chain = function() {
        return this._chain = !0, this;
    }, j.prototype.value = function() {
        return this._wrapped;
    };
}.call(this);

var Backbone = Backbone || {};

!function() {
    var t = [].slice, e = /\s+/, n = function(t, n, i, r) {
        if (!i) return !0;
        if ("object" == typeof i) for (var o in i) t[n].apply(t, [ o, i[o] ].concat(r)); else {
            if (!e.test(i)) return !0;
            i = i.split(e), o = 0;
            for (var s = i.length; s > o; o++) t[n].apply(t, [ i[o] ].concat(r));
        }
    }, i = function(t, e, n) {
        var i;
        t = -1;
        var r = e.length;
        switch (n.length) {
            case 0:
                for (;++t < r; ) (i = e[t]).callback.call(i.ctx);
                break;

            case 1:
                for (;++t < r; ) (i = e[t]).callback.call(i.ctx, n[0]);
                break;

            case 2:
                for (;++t < r; ) (i = e[t]).callback.call(i.ctx, n[0], n[1]);
                break;

            case 3:
                for (;++t < r; ) (i = e[t]).callback.call(i.ctx, n[0], n[1], n[2]);
                break;

            default:
                for (;++t < r; ) (i = e[t]).callback.apply(i.ctx, n);
        }
    }, r = Backbone.Events = {
        on: function(t, e, i) {
            return n(this, "on", t, [ e, i ]) && e ? (this._events || (this._events = {}), (this._events[t] || (this._events[t] = [])).push({
                callback: e,
                context: i,
                ctx: i || this
            }), this) : this;
        },
        once: function(t, e, i) {
            if (!n(this, "once", t, [ e, i ]) || !e) return this;
            var r = this, o = _.once(function() {
                r.off(t, o), e.apply(this, arguments);
            });
            return o._callback = e, this.on(t, o, i), this;
        },
        off: function(t, e, i) {
            var r, o, s, a, u, c, l, h;
            if (!this._events || !n(this, "off", t, [ e, i ])) return this;
            if (!t && !e && !i) return this._events = {}, this;
            for (a = t ? [ t ] : _.keys(this._events), u = 0, c = a.length; c > u; u++) if (t = a[u],
                    r = this._events[t]) {
                if (s = [], e || i) for (l = 0, h = r.length; h > l; l++) o = r[l], (e && e !== (o.callback._callback || o.callback) || i && i !== o.context) && s.push(o);
                this._events[t] = s;
            }
            return this;
        },
        trigger: function(e) {
            if (!this._events) return this;
            var r = t.call(arguments, 1);
            if (!n(this, "trigger", e, r)) return this;
            var o = this._events[e], s = this._events.all;
            return o && i(this, o, r), s && i(this, s, arguments), this;
        },
        listenTo: function(t, e, n) {
            var i = this._listeners || (this._listeners = {}), r = t._listenerId || (t._listenerId = _.uniqueId("l"));
            return i[r] = t, t.on(e, n || this, this), this;
        },
        stopListening: function(t, e, n) {
            var i = this._listeners;
            if (i) {
                if (t) t.off(e, n, this), !e && !n && delete i[t._listenerId]; else {
                    for (var r in i) i[r].off(null, null, this);
                    this._listeners = {};
                }
                return this;
            }
        }
    };
    r.bind = r.on, r.unbind = r.off;
}(), function() {
    for (var t = 0, e = [ "ms", "moz", "webkit", "o" ], n = 0; n < e.length && !window.requestAnimationFrame; ++n) window.requestAnimationFrame = window[e[n] + "RequestAnimationFrame"],
        window.cancelAnimationFrame = window[e[n] + "CancelAnimationFrame"] || window[e[n] + "CancelRequestAnimationFrame"];
    window.requestAnimationFrame || (window.requestAnimationFrame = function(e) {
        var n = new Date().getTime(), i = Math.max(0, 16 - (n - t)), r = window.setTimeout(function() {
            e(n + i);
        }, i);
        return t = n + i, r;
    }), window.cancelAnimationFrame || (window.cancelAnimationFrame = function(t) {
        clearTimeout(t);
    });
}(), function() {
    function t() {
        var t = document.body.getBoundingClientRect(), e = this.width = t.width, t = this.height = t.height;
        this.renderer.setSize(e, t), this.trigger(d.Events.resize, e, t);
    }
    var e = this, n = e.Two || {}, i = Math.sin, r = Math.cos, o = Math.atan2, s = Math.sqrt, a = Math.abs, u = Math.PI, c = 2 * u, l = u / 2, h = Math.pow, f = {
        hasEventListeners: _.isFunction(e.addEventListener),
        bind: function(t, e, n, i) {
            return this.hasEventListeners ? t.addEventListener(e, n, !!i) : t.attachEvent("on" + e, n),
                this;
        },
        unbind: function(t, e, n, i) {
            return this.hasEventListeners ? t.removeEventListeners(e, n, !!i) : t.detachEvent("on" + e, n),
                this;
        }
    }, d = e.Two = function(e) {
        e = _.defaults(e || {}, {
            fullscreen: !1,
            width: 640,
            height: 480,
            type: d.Types.svg,
            autostart: !1
        }), this.type = e.type, this.renderer = new d[this.type](this), d.Utils.setPlaying.call(this, e.autostart),
            this.frameCount = 0, e.fullscreen ? (e = _.bind(t, this), _.extend(document.body.style, {
            overflow: "hidden",
            margin: 0,
            padding: 0,
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            position: "fixed"
        }), _.extend(this.renderer.domElement.style, {
            display: "block",
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            position: "fixed"
        }), f.bind(window, "resize", e), e()) : (this.renderer.setSize(e.width, e.height),
            this.width = e.width, this.height = e.height), this.scene = new d.Group(), this.renderer.add(this.scene),
            d.Instances.push(this);
    };
    _.extend(d, {
        Array: e.Float32Array || Array,
        Types: {
            webgl: "WebGLRenderer",
            svg: "SVGRenderer",
            canvas: "CanvasRenderer"
        },
        Properties: {
            hierarchy: "hierarchy",
            demotion: "demotion"
        },
        Events: {
            play: "play",
            pause: "pause",
            update: "update",
            render: "render",
            resize: "resize",
            change: "change"
        },
        Resolution: 8,
        Instances: [],
        noConflict: function() {
            return e.Two = n, this;
        },
        Utils: {
            Curve: {
                CollinearityEpsilon: h(10, -30),
                RecursionLimit: 16,
                CuspLimit: 0,
                Tolerance: {
                    distance: .25,
                    angle: 0,
                    epsilon: .01
                }
            },
            setPlaying: function(t) {
                _.defer(_.bind(function() {
                    this.playing = !!t;
                }, this));
            },
            applySvgAttributes: function(t, e) {
                return e.cap = "butt", e.join = "bevel", _.each(t.attributes, function(t) {
                    switch (t.nodeName) {
                        case "visibility":
                            e.visible = !!t.nodeValue;
                            break;

                        case "stroke-linecap":
                            e.cap = t.nodeValue;
                            break;

                        case "stroke-linejoin":
                            e.join = t.nodeValue;
                            break;

                        case "stroke-miterlimit":
                            e.miter = t.nodeValue;
                            break;

                        case "stroke-width":
                            e.linewidth = parseFloat(t.nodeValue);
                            break;

                        case "stroke-opacity":
                        case "fill-opacity":
                            e.opacity = t.nodeValue;
                            break;

                        case "fill":
                            e.fill = t.nodeValue;
                            break;

                        case "stroke":
                            e.stroke = t.nodeValue;
                    }
                }), e;
            },
            read: {
                svg: function() {
                    return d.Utils.read.g.apply(this, arguments);
                },
                g: function(t) {
                    var e = new d.Group();
                    return this.add(e), _.each(t.childNodes, function(t) {
                        if (t.localName) {
                            var n = t.localName.toLowerCase();
                            n in d.Utils.read && (t = d.Utils.read[n].call(this, t), e.add(t));
                        }
                    }, this), d.Utils.applySvgAttributes(t, e);
                },
                polygon: function(t, e) {
                    var n = t.points, i = _.map(_.range(n.numberOfItems), function(t) {
                        return t = n.getItem(t), new d.Vector(t.x, t.y);
                    }), i = new d.Polygon(i, !e).noStroke();
                    return d.Utils.applySvgAttributes(t, i);
                },
                polyline: function(t) {
                    return d.Utils.read.polygon(t, !0);
                },
                path: function(t) {
                    var e = t.getAttribute("d"), e = _.flatten(_.map(_.compact(e.split(/M/g)), function(t) {
                        return t = _.map(_.compact(t.split(/m/g)), function(t, e) {
                            return 0 >= e ? t : "m" + t;
                        }), t[0] = "M" + t[0], t;
                    })), n = new d.Vector(), i = new d.Vector();
                    return _.map(e, function(e) {
                        var r, o = !1, s = !1;
                        return e = _.flatten(_.map(e.match(/[a-z][^a-z]*/gi), function(t) {
                            var e, a, u = t[0], c = u.toLowerCase();
                            r = t.slice(1).trim().split(/[\s,]+|(?=[+\-])/), o = u === c;
                            var l, h, f;
                            switch (c) {
                                case "z":
                                    s = !0;
                                    break;

                                case "m":
                                case "l":
                                    a = parseFloat(r[0]), e = parseFloat(r[1]), e = new d.Vector(a, e), o && e.addSelf(n),
                                        n.copy(e);
                                    break;

                                case "h":
                                case "v":
                                    a = "h" === c ? "x" : "y", t = "x" === a ? "y" : "x", e = new d.Vector(), e[a] = parseFloat(r[0]),
                                        e[t] = n[t], o && (e[a] += n[a]), n.copy(e);
                                    break;

                                case "s":
                                case "c":
                                    e = n.x, t = n.y, "c" === c ? (u = parseFloat(r[0]), a = parseFloat(r[1]), c = parseFloat(r[2]),
                                        l = parseFloat(r[3]), h = parseFloat(r[4]), f = parseFloat(r[5])) : (l = new d.Vector().copy(n).subSelf(i),
                                        u = parseFloat(l.x), a = parseFloat(l.y), c = parseFloat(r[0]), l = parseFloat(r[1]),
                                        h = parseFloat(r[2]), f = parseFloat(r[3])), o && (u += e, a += t, c += e, l += t,
                                        h += e, f += t), e = d.Utils.subdivide(e, t, u, a, c, l, h, f), n.set(h, f), i.set(c, l);
                                    break;

                                case "t":
                                case "q":
                                    e = n.x, t = n.y, i.isZero() ? (u = e, a = t) : (u = i.x, t = i.y), "q" === c ? (c = parseFloat(r[0]),
                                        l = parseFloat(r[1]), h = parseFloat(r[1]), f = parseFloat(r[2])) : (l = new d.Vector().copy(n).subSelf(i),
                                        c = parseFloat(l.x), l = parseFloat(l.y), h = parseFloat(r[0]), f = parseFloat(r[1])),
                                    o && (u += e, a += t, c += e, l += t, h += e, f += t), e = d.Utils.subdivide(e, t, u, a, c, l, h, f),
                                        n.set(h, f), i.set(c, l);
                                    break;

                                case "a":
                                    throw new d.Utils.Error("not yet able to interpret Elliptical Arcs.");
                            }
                            return e;
                        })), _.isUndefined(e[e.length - 1]) && e.pop(), e = new d.Polygon(e, s).noStroke(),
                            d.Utils.applySvgAttributes(t, e);
                    });
                },
                circle: function(t) {
                    var e = parseFloat(t.getAttribute("cx")), n = parseFloat(t.getAttribute("cy")), o = parseFloat(t.getAttribute("r")), s = d.Resolution, a = _.map(_.range(s), function(t) {
                        var e = t / s * c;
                        return t = o * r(e), e = o * i(e), new d.Vector(t, e);
                    }, this), a = new d.Polygon(a, !0, !0).noStroke();
                    return a.translation.set(e, n), d.Utils.applySvgAttributes(t, a);
                },
                ellipse: function(t) {
                    var e = parseFloat(t.getAttribute("cx")), n = parseFloat(t.getAttribute("cy")), o = parseFloat(t.getAttribute("rx")), s = parseFloat(t.getAttribute("ry")), a = d.Resolution, u = _.map(_.range(a), function(t) {
                        var e = t / a * c;
                        return t = o * r(e), e = s * i(e), new d.Vector(t, e);
                    }, this), u = new d.Polygon(u, !0, !0).noStroke();
                    return u.translation.set(e, n), d.Utils.applySvgAttributes(t, u);
                },
                rect: function(t) {
                    var e = parseFloat(t.getAttribute("x")), n = parseFloat(t.getAttribute("y")), i = parseFloat(t.getAttribute("width")), r = parseFloat(t.getAttribute("height")), i = i / 2, r = r / 2, o = [ new d.Vector(i, r), new d.Vector(-i, r), new d.Vector(-i, -r), new d.Vector(i, -r) ], o = new d.Polygon(o, !0).noStroke();
                    return o.translation.set(e + i, n + r), d.Utils.applySvgAttributes(t, o);
                },
                line: function(t) {
                    var e = parseFloat(t.getAttribute("x1")), n = parseFloat(t.getAttribute("y1")), i = parseFloat(t.getAttribute("x2")), r = parseFloat(t.getAttribute("y2")), i = (i - e) / 2, r = (r - n) / 2, o = [ new d.Vector(-i, -r), new d.Vector(i, r) ], o = new d.Polygon(o).noFill();
                    return o.translation.set(e + i, n + r), d.Utils.applySvgAttributes(t, o);
                }
            },
            subdivide: function(t, e, n, i, r, s, l, h, f) {
                var p, m = d.Utils.Curve.CollinearityEpsilon, g = d.Utils.Curve.RecursionLimit, v = d.Utils.Curve.CuspLimit, y = d.Utils.Curve.Tolerance;
                if (f = f || 0, f > g) return [];
                var g = (t + n) / 2, b = (e + i) / 2, x = (n + r) / 2, w = (i + s) / 2, _ = (r + l) / 2, k = (s + h) / 2, C = (g + x) / 2, E = (b + w) / 2, x = (x + _) / 2, w = (w + k) / 2, T = (C + x) / 2, A = (E + w) / 2;
                p = l - t;
                var N = h - e, S = a((n - l) * N - (i - h) * p), F = a((r - l) * N - (s - h) * p);
                if (f > 0) {
                    if (S > m && F > m && (S + F) * (S + F) <= y.distance * (p * p + N * N)) {
                        if (y.angle < y.epsilon) return [ new d.Vector(T, A) ];
                        if (p = o(s - i, r - n), m = a(p - o(i - e, n - t)), p = a(o(h - s, l - r) - p),
                            m >= u && (m = c - m), p >= u && (p = c - p), m + p < y.angle) return [ new d.Vector(T, A) ];
                        if (0 !== v) {
                            if (m > v) return [ new d.Vector(n, i) ];
                            if (p > v) return [ new d.Vector(r, s) ];
                        }
                    }
                } else if (S > m) if (S * S <= y.distance * (p * p + N * N)) {
                    if (y.angle < y.epsilon) return [ new d.Vector(T, A) ];
                    if (m = a(o(s - i, r - n) - o(i - e, n - t)), m >= u && (m = c - m), m < y.angle) return [ new d.Vector(n, i), new d.Vector(r, s) ];
                    if (0 !== v && m > v) return [ new d.Vector(n, i) ];
                } else if (F > m) {
                    if (F * F <= y.distance * (p * p + N * N)) {
                        if (y.angle < y.epsilon) return [ new d.Vector(T, A) ];
                        if (m = a(o(h - s, l - r) - o(s - i, r - n)), m >= u && (m = c - m), m < y.angle) return [ new d.Vector(n, i), new d.Vector(r, s) ];
                        if (0 !== v && m > v) return [ new d.Vector2(r, s) ];
                    }
                } else if (p = T - (t + l) / 2, N = A - (e + h) / 2, p * p + N * N <= y.distance) return [ new d.Vector(T, A) ];
                return d.Utils.subdivide(t, e, g, b, C, E, T, A, f + 1).concat(d.Utils.subdivide(T, A, x, w, _, k, l, h, f + 1));
            },
            getCurveFromPoints: function(t, e) {
                for (var n = [], i = t.length, r = i - 1, o = 0; i > o; o++) {
                    var s = t[o], a = {
                        x: s.x,
                        y: s.y
                    };
                    n.push(a);
                    var s = e ? y(o - 1, i) : Math.max(o - 1, 0), u = e ? y(o + 1, i) : Math.min(o + 1, r);
                    v(t[s], a, t[u]), !a.u.x && !a.u.y && (a.u.x = a.x, a.u.y = a.y), !a.v.x && !a.v.y && (a.v.x = a.x,
                        a.v.y = a.y);
                }
                return n;
            },
            getControlPoints: function(t, e, n) {
                var o = g(t, e), s = g(n, e);
                t = p(t, e), n = p(n, e);
                var a = (o + s) / 2;
                return 1e-4 > t || 1e-4 > n ? (e.u = {
                    x: e.x,
                    y: e.y
                }, e.v = {
                    x: e.x,
                    y: e.y
                }, e) : (t *= .33, n *= .33, a = o > s ? a + l : a - l, o = {
                    x: e.x + r(a) * t,
                    y: e.y + i(a) * t
                }, a -= u, s = {
                    x: e.x + r(a) * n,
                    y: e.y + i(a) * n
                }, e.u = o, e.v = s, e);
            },
            angleBetween: function(t, e) {
                return o(t.y - e.y, t.x - e.x);
            },
            distanceBetweenSquared: function(t, e) {
                var n = t.x - e.x, i = t.y - e.y;
                return n * n + i * i;
            },
            distanceBetween: function(t, e) {
                return s(m(t, e));
            },
            mod: function(t, e) {
                for (;0 > t; ) t += e;
                return t % e;
            },
            Error: function(t) {
                this.name = "two.js", this.message = t;
            }
        }
    }), d.Utils.Error.prototype = Error(), d.Utils.Error.prototype.constructor = d.Utils.Error;
    var p = d.Utils.distanceBetween, m = d.Utils.distanceBetweenSquared, g = d.Utils.angleBetween, v = d.Utils.getControlPoints, y = d.Utils.mod;
    _.extend(d.prototype, Backbone.Events, {
        appendTo: function(t) {
            return t.appendChild(this.renderer.domElement), this;
        },
        play: function() {
            return d.Utils.setPlaying.call(this, !0), this.trigger(d.Events.play);
        },
        pause: function() {
            return this.playing = !1, this.trigger(d.Events.pause);
        },
        update: function() {
            var t = !!this._lastFrame, e = (window.performance && window.performance.now ? window.performance : Date).now();
            this.frameCount++, t && (this.timeDelta = parseFloat((e - this._lastFrame).toFixed(3))),
                this._lastFrame = e;
            var t = this.width, e = this.height, n = this.renderer;
            return (t !== n.width || e !== n.height) && n.setSize(t, e), this.trigger(d.Events.update, this.frameCount, this.timeDelta),
                this.render();
        },
        render: function() {
            return this.renderer.render(), this.trigger(d.Events.render, this.frameCount);
        },
        add: function(t) {
            var e = t;
            return _.isArray(t) || (e = _.toArray(arguments)), this.scene.add(e), this;
        },
        remove: function(t) {
            var e = t;
            return _.isArray(t) || (e = _.toArray(arguments)), this.scene.remove(e), this;
        },
        clear: function() {
            return _.each(this.scene.children, function(t) {
                t.remove();
            }), this;
        },
        makeLine: function(t, e, n, i) {
            n = (n - t) / 2, i = (i - e) / 2;
            var r = [ new d.Vector(-n, -i), new d.Vector(n, i) ], r = new d.Polygon(r).noFill();
            return r.translation.set(t + n, e + i), this.scene.add(r), r;
        },
        makeRectangle: function(t, e, n, i) {
            return n /= 2, i /= 2, i = [ new d.Vector(n, i), new d.Vector(-n, i), new d.Vector(-n, -i), new d.Vector(n, -i) ],
                i = new d.Polygon(i, !0), i.translation.set(t, e), this.scene.add(i), i;
        },
        makeCircle: function(t, e, n) {
            return this.makeEllipse(t, e, n, n);
        },
        makeEllipse: function(t, e, n, o) {
            var s = d.Resolution, a = _.map(_.range(s), function(t) {
                var e = t / s * c;
                return t = n * r(e), e = o * i(e), new d.Vector(t, e);
            }, this), a = new d.Polygon(a, !0, !0);
            return a.translation.set(t, e), this.scene.add(a), a;
        },
        makeCurve: function(t) {
            var e = arguments.length, n = t;
            if (!_.isArray(t)) for (var n = [], i = 0; e > i; i += 2) {
                var r = arguments[i];
                if (!_.isNumber(r)) break;
                n.push(new d.Vector(r, arguments[i + 1]));
            }
            var e = arguments[e - 1], n = new d.Polygon(n, !(_.isBoolean(e) && e), !0), e = n.getBoundingClientRect(), o = e.left + e.width / 2, s = e.top + e.height / 2;
            return _.each(n.vertices, function(t) {
                t.x -= o, t.y -= s;
            }), n.translation.set(o, s), this.scene.add(n), n;
        },
        makePolygon: function(t) {
            var e = arguments.length, n = t;
            if (!_.isArray(t)) for (var n = [], i = 0; e > i; i += 2) {
                var r = arguments[i];
                if (!_.isNumber(r)) break;
                n.push(new d.Vector(r, arguments[i + 1]));
            }
            return e = arguments[e - 1], n = new d.Polygon(n, !(_.isBoolean(e) && e)), n.center(),
                this.scene.add(n), n;
        },
        makeGroup: function(t) {
            var e = t;
            _.isArray(t) || (e = _.toArray(arguments));
            var n = new d.Group();
            return this.scene.add(n), n.add(e), n;
        },
        interpret: function(t) {
            var e = t.tagName.toLowerCase();
            return e in d.Utils.read ? (t = d.Utils.read[e].call(this, t), this.add(t), t) : null;
        }
    }), function() {
        _.each(d.Instances, function(t) {
            t.playing && t.update();
        }), requestAnimationFrame(arguments.callee);
    }();
}(), function() {
    var t = Two.Vector = function(t, e) {
        t = t || 0, e = e || 0, Object.defineProperty(this, "x", {
            get: function() {
                return t;
            },
            set: function(e) {
                t = e, this.trigger(Two.Events.change, "x");
            }
        }), Object.defineProperty(this, "y", {
            get: function() {
                return e;
            },
            set: function(t) {
                e = t, this.trigger(Two.Events.change, "y");
            }
        });
    };
    _.extend(t, {}), _.extend(t.prototype, Backbone.Events, {
        set: function(t, e) {
            return this.x = t, this.y = e, this;
        },
        copy: function(t) {
            return this.x = t.x, this.y = t.y, this;
        },
        clear: function() {
            return this.y = this.x = 0, this;
        },
        clone: function() {
            return new t(this.x, this.y);
        },
        add: function(t, e) {
            return this.x = t.x + e.x, this.y = t.y + e.y, this;
        },
        addSelf: function(t) {
            return this.x += t.x, this.y += t.y, this;
        },
        sub: function(t, e) {
            return this.x = t.x - e.x, this.y = t.y - e.y, this;
        },
        subSelf: function(t) {
            return this.x -= t.x, this.y -= t.y, this;
        },
        multiplySelf: function(t) {
            return this.x *= t.x, this.y *= t.y, this;
        },
        multiplyScalar: function(t) {
            return this.x *= t, this.y *= t, this;
        },
        divideScalar: function(t) {
            return t ? (this.x /= t, this.y /= t) : this.set(0, 0), this;
        },
        negate: function() {
            return this.multiplyScalar(-1);
        },
        dot: function(t) {
            return this.x * t.x + this.y * t.y;
        },
        lengthSquared: function() {
            return this.x * this.x + this.y * this.y;
        },
        length: function() {
            return Math.sqrt(this.lengthSquared());
        },
        normalize: function() {
            return this.divideScalar(this.length());
        },
        distanceTo: function(t) {
            return Math.sqrt(this.distanceToSquared(t));
        },
        distanceToSquared: function(t) {
            var e = this.x - t.x;
            return t = this.y - t.y, e * e + t * t;
        },
        setLength: function(t) {
            return this.normalize().multiplyScalar(t);
        },
        equals: function(t) {
            return 1e-4 > this.distanceTo(t);
        },
        lerp: function(t, e) {
            return this.set((t.x - this.x) * e + this.x, (t.y - this.y) * e + this.y);
        },
        isZero: function() {
            return 1e-4 > this.length();
        }
    });
}(), function() {
    _.range(6);
    var t = Math.cos, e = Math.sin, n = Math.tan, r = Two.Matrix = function(t, e, n, i, r, o) {
        this.elements = new Two.Array(9);
        var s = t;
        _.isArray(s) || (s = _.toArray(arguments)), this.identity().set(s);
    };
    _.extend(r, {
        Identity: [ 1, 0, 0, 0, 1, 0, 0, 0, 1 ],
        Multiply: function(t, e) {
            if (3 >= e.length) {
                var n = e[0] || 0, i = e[1] || 0, r = e[2] || 0;
                return {
                    x: t[0] * n + t[1] * i + t[2] * r,
                    y: t[3] * n + t[4] * i + t[5] * r,
                    z: t[6] * n + t[7] * i + t[8] * r
                };
            }
            var n = t[0], i = t[1], r = t[2], o = t[3], s = t[4], a = t[5], u = t[6], c = t[7], l = t[8], h = e[0], f = e[1], d = e[2], p = e[3], m = e[4], g = e[5], v = e[6], y = e[7], b = e[8];
            return [ n * h + i * p + r * v, n * f + i * m + r * y, n * d + i * g + r * b, o * h + s * p + a * v, o * f + s * m + a * y, o * d + s * g + a * b, u * h + c * p + l * v, u * f + c * m + l * y, u * d + c * g + l * b ];
        }
    }), _.extend(r.prototype, Backbone.Events, {
        set: function(t, e, n, i, r, o) {
            var s = t;
            return _.isArray(s) || (s = _.toArray(arguments)), _.each(s, function(t, e) {
                _.isNumber(t) && (this.elements[e] = t);
            }, this), this.trigger(Two.Events.change);
        },
        identity: function() {
            return this.set(r.Identity), this;
        },
        multiply: function(t, e, n, i, r, o, s, a, u) {
            var c = arguments, l = c.length;
            return 1 >= l ? (_.each(this.elements, function(e, n) {
                this.elements[n] = e * t;
            }, this), this.trigger(Two.Events.change)) : 3 >= l ? (t = t || 0, e = e || 0, n = n || 0,
                r = this.elements, {
                x: r[0] * t + r[1] * e + r[2] * n,
                y: r[3] * t + r[4] * e + r[5] * n,
                z: r[6] * t + r[7] * e + r[8] * n
            }) : (l = this.elements, A0 = l[0], A1 = l[1], A2 = l[2], A3 = l[3], A4 = l[4],
                A5 = l[5], A6 = l[6], A7 = l[7], A8 = l[8], B0 = c[0], B1 = c[1], B2 = c[2], B3 = c[3],
                B4 = c[4], B5 = c[5], B6 = c[6], B7 = c[7], B8 = c[8], this.elements[0] = A0 * B0 + A1 * B3 + A2 * B6,
                this.elements[1] = A0 * B1 + A1 * B4 + A2 * B7, this.elements[2] = A0 * B2 + A1 * B5 + A2 * B8,
                this.elements[3] = A3 * B0 + A4 * B3 + A5 * B6, this.elements[4] = A3 * B1 + A4 * B4 + A5 * B7,
                this.elements[5] = A3 * B2 + A4 * B5 + A5 * B8, this.elements[6] = A6 * B0 + A7 * B3 + A8 * B6,
                this.elements[7] = A6 * B1 + A7 * B4 + A8 * B7, this.elements[8] = A6 * B2 + A7 * B5 + A8 * B8,
                this.trigger(Two.Events.change));
        },
        inverse: function(t) {
            var e = this.elements;
            t = t || new Two.Matrix();
            var n = e[0], i = e[1], r = e[2], o = e[3], s = e[4], a = e[5], u = e[6], c = e[7], e = e[8], l = e * s - a * c, h = -e * o + a * u, f = c * o - s * u, d = n * l + i * h + r * f;
            return d ? (d = 1 / d, t.elements[0] = l * d, t.elements[1] = (-e * i + r * c) * d,
                t.elements[2] = (a * i - r * s) * d, t.elements[3] = h * d, t.elements[4] = (e * n - r * u) * d,
                t.elements[5] = (-a * n + r * o) * d, t.elements[6] = f * d, t.elements[7] = (-c * n + i * u) * d,
                t.elements[8] = (s * n - i * o) * d, t) : null;
        },
        scale: function(t, e) {
            return 1 >= arguments.length && (e = t), this.multiply(t, 0, 0, 0, e, 0, 0, 0, 1);
        },
        rotate: function(n) {
            var i = t(n);
            return n = e(n), this.multiply(i, -n, 0, n, i, 0, 0, 0, 1);
        },
        translate: function(t, e) {
            return this.multiply(1, 0, t, 0, 1, e, 0, 0, 1);
        },
        skewX: function(t) {
            return t = n(t), this.multiply(1, t, 0, 0, 1, 0, 0, 0, 1);
        },
        skewY: function(t) {
            return t = n(t), this.multiply(1, 0, 0, t, 1, 0, 0, 0, 1);
        },
        toString: function() {
            return this.toArray().join(" ");
        },
        toArray: function(t) {
            var e = this.elements, n = parseFloat(e[0].toFixed(3)), i = parseFloat(e[1].toFixed(3)), r = parseFloat(e[2].toFixed(3)), o = parseFloat(e[3].toFixed(3)), s = parseFloat(e[4].toFixed(3)), a = parseFloat(e[5].toFixed(3));
            if (t) {
                t = parseFloat(e[6].toFixed(3));
                var u = parseFloat(e[7].toFixed(3)), e = parseFloat(e[8].toFixed(3));
                return [ n, o, t, i, s, u, r, a, e ];
            }
            return [ n, o, i, s, r, a ];
        },
        clone: function() {
            var t = this.elements[0], e = this.elements[1], n = this.elements[2], r = this.elements[3], o = this.elements[4], s = this.elements[5];
            return g = this.elements[6], h = this.elements[7], i = this.elements[8], new Two.Matrix(t, e, n, r, o, s, g, h, i);
        }
    });
}(), function() {
    function t(t) {
        var e = {}, n = t.id, o = t.translation, s = t.rotation, a = t.scale, u = t.stroke, c = t.linewidth, l = t.fill, h = t.opacity, f = t.visible, d = t.cap, p = t.join, m = t.miter, g = t.curved, v = t.closed, y = t.vertices;
        return n && (e.id = r.Identifier + n), o && _.isNumber(a) && _.isNumber(s) && (e.transform = "matrix(" + t._matrix.toString() + ")"),
        u && (e.stroke = u), l && (e.fill = l), h && (e["stroke-opacity"] = e["fill-opacity"] = h),
            e.visibility = f ? "visible" : "hidden", d && (e["stroke-linecap"] = d), p && (e["stroke-linejoin"] = p),
        m && (e["stroke-miterlimit"] = m), c && (e["stroke-width"] = c), y && (e.d = i.toString(y, v, g)),
            e;
    }
    var e = Two.Utils.getCurveFromPoints, n = Two.Utils.mod, i = {
        version: 1.1,
        ns: "http://www.w3.org/2000/svg",
        xlink: "http://www.w3.org/1999/xlink",
        createElement: function(t, e) {
            var n = t.toLowerCase(), i = document.createElementNS(this.ns, n);
            return "svg" === n && (e = _.defaults(e || {}, {
                version: this.version
            })), _.isObject(e) && this.setAttributes(i, e), i;
        },
        setAttributes: function(t, e) {
            return _.each(e, function(t, e) {
                this.setAttribute(e, t);
            }, t), this;
        },
        removeAttributes: function(t, e) {
            return _.each(e, function(t) {
                this.removeAttribute(t);
            }, t), this;
        },
        toString: function(t, i, r) {
            var o = t.length, s = o - 1;
            if (!r) return _.map(t, function(t, e) {
                var n;
                return n = (0 >= e ? "M" : "L") + (" " + t.x.toFixed(3) + " " + t.y.toFixed(3)),
                e >= s && i && (n += " Z"), n;
            }).join(" ");
            var a = e(t, i);
            return _.map(a, function(t, e) {
                var r, u = i ? n(e - 1, o) : Math.max(e - 1, 0), c = i ? n(e + 1, o) : Math.min(e + 1, s);
                r = a[u];
                var c = a[c], u = r.v.x.toFixed(3), l = r.v.y.toFixed(3), h = t.u.x.toFixed(3), f = t.u.y.toFixed(3), d = t.x.toFixed(3), p = t.y.toFixed(3);
                return r = 0 >= e ? "M " + d + " " + p : "C " + u + " " + l + " " + h + " " + f + " " + d + " " + p,
                e >= s && i && (u = t.v.x.toFixed(3), l = t.v.y.toFixed(3), h = c.u.x.toFixed(3),
                    f = c.u.y.toFixed(3), d = c.x.toFixed(3), p = c.y.toFixed(3), r = r + (" C " + u + " " + l + " " + h + " " + f + " " + d + " " + p) + " Z"),
                    r;
            }).join(" ");
        }
    }, r = Two[Two.Types.svg] = function() {
        this.count = 0, this.domElement = i.createElement("svg"), this.elements = [], this.domElement.style.visibility = "hidden",
            this.unveil = _.once(_.bind(function() {
                this.domElement.style.visibility = "visible";
            }, this));
    };
    _.extend(r, {
        Identifier: "two-"
    }), _.extend(r.prototype, Backbone.Events, {
        setSize: function(t, e) {
            return this.width = t, this.height = e, i.setAttributes(this.domElement, {
                width: t,
                height: e
            }), this;
        },
        add: function(e) {
            var n = e, r = this.elements, o = this.domElement;
            return _.isArray(e) || (n = _.map(arguments, function(t) {
                return t;
            })), _.each(n, function(e) {
                var n;
                if (n = e instanceof Two.Group, _.isUndefined(e.id)) {
                    var s = this.count;
                    this.count++, e.id = s;
                }
                n ? (n = "g", _.isUndefined(e.parent) && (e.parent = this, e.unbind(Two.Events.change).bind(Two.Events.change, _.bind(this.update, this))),
                    e = t(e), delete e.stroke, delete e.fill, delete e["fill-opacity"], delete e["stroke-opacity"],
                    delete e["stroke-linecap"], delete e["stroke-linejoin"], delete e["stroke-miterlimit"],
                    delete e["stroke-width"]) : (n = "path", e = t(e)), e = i.createElement(n, e), o.appendChild(e),
                    r.push(e);
            }, this), this;
        },
        update: function(t, e, n, r, o) {
            var s = this.elements, a = s[t];
            switch (e) {
                case Two.Properties.hierarchy:
                    _.each(n, function(t) {
                        a.appendChild(s[t]);
                    });
                    break;

                case Two.Properties.demotion:
                    _.each(n, function(t) {
                        a.removeChild(s[t]);
                    });
                    break;

                default:
                    t: {
                        switch (t = e) {
                            case "matrix":
                                t = "transform", n = "matrix(" + n.toString() + ")";
                                break;

                            case "visible":
                                t = "visibility", n = n ? "visible" : "hidden";
                                break;

                            case "cap":
                                t = "stroke-linecap";
                                break;

                            case "join":
                                t = "stroke-linejoin";
                                break;

                            case "miter":
                                t = "stroke-miterlimit";
                                break;

                            case "linewidth":
                                t = "stroke-width";
                                break;

                            case "vertices":
                                t = "d", n = i.toString(n, r, o);
                                break;

                            case "opacity":
                                i.setAttributes(a, {
                                    "stroke-opacity": n,
                                    "fill-opacity": n
                                });
                                break t;
                        }
                        a.setAttribute(t, n);
                    }
            }
            return this;
        },
        render: function() {
            return this.unveil(), this;
        }
    });
}(), function() {
    var t = Two.Utils.getCurveFromPoints, e = Two.Utils.mod, n = function(t) {
        _.each(t, function(t, e) {
            this[e] = t;
        }, this), this.children = {};
    };
    _.extend(n.prototype, {
        appendChild: function(t) {
            var e = t.parent, n = t.id;
            return _.isUndefined(e) || delete e.children[n], this.children[n] = t, t.parent = this,
                this;
        },
        removeChild: function(t) {
            return delete this.children[t.id], this;
        },
        render: function(t) {
            var e = this.matrix;
            return t.save(), t.transform(e[0], e[1], e[2], e[3], e[4], e[5]), _.each(this.children, function(e) {
                e.render(t);
            }), t.restore(), this;
        }
    });
    var i = function(t) {
        _.each(t, function(t, e) {
            this[e] = t;
        }, this);
    };
    _.extend(i.prototype, {
        render: function(t) {
            var n = this.matrix, i = this.stroke, r = this.linewidth, o = this.fill, s = this.opacity, a = this.cap, u = this.join, c = this.miter, l = this.curved, h = this.closed, f = this.commands, d = f.length, p = d - 1;
            return this.visible ? (t.save(), n && t.transform(n[0], n[1], n[2], n[3], n[4], n[5]),
            o && (t.fillStyle = o), i && (t.strokeStyle = i), r && (t.lineWidth = r), c && (t.miterLimit = c),
            u && (t.lineJoin = u), a && (t.lineCap = a), _.isNumber(s) && (t.globalAlpha = s),
                t.beginPath(), _.each(f, function(n, i) {
                var r = n.x.toFixed(3), o = n.y.toFixed(3);
                if (l) {
                    var s = h ? e(i - 1, d) : Math.max(i - 1, 0), a = h ? e(i + 1, d) : Math.min(i + 1, p), u = f[s], a = f[a], s = u.v.x.toFixed(3), u = u.v.y.toFixed(3), c = n.u.x.toFixed(3), m = n.u.y.toFixed(3);
                    0 >= i ? t.moveTo(r, o) : (t.bezierCurveTo(s, u, c, m, r, o), i >= p && h && (s = n.v.x.toFixed(3),
                        u = n.v.y.toFixed(3), c = a.u.x.toFixed(3), m = a.u.y.toFixed(3), r = a.x.toFixed(3),
                        o = a.y.toFixed(3), t.bezierCurveTo(s, u, c, m, r, o)));
                } else 0 >= i ? t.moveTo(r, o) : t.lineTo(r, o);
            }), h && !l && t.closePath(), t.fill(), t.stroke(), void t.restore()) : this;
        }
    });
    var r = {
        toArray: function(e, n, i) {
            return n ? t(e, i) : _.map(e, function(t) {
                return {
                    x: t.x,
                    y: t.y
                };
            });
        }
    }, o = Two[Two.Types.canvas] = function() {
        this.count = 0, this.domElement = document.createElement("canvas"), this.ctx = this.domElement.getContext("2d"),
            this.elements = [], this.stage = null;
    };
    _.extend(o, {}), _.extend(o, {
        Group: n,
        Element: i,
        getStyles: function(t) {
            var e = {}, n = t.id, i = t._matrix, o = t.stroke, s = t.linewidth, a = t.fill, u = t.opacity, c = t.visible, l = t.cap, h = t.join, f = t.miter, d = t.curved, p = t.closed;
            return t = t.vertices, n && (e.id = n), _.isObject(i) && (e.matrix = i.toArray()),
            o && (e.stroke = o), a && (e.fill = a), _.isNumber(u) && (e.opacity = u), l && (e.cap = l),
            h && (e.join = h), f && (e.miter = f), s && (e.linewidth = s), t && (e.commands = r.toArray(t, d, p)),
                e.visible = !!c, e.curved = !!d, e.closed = !!p, e;
        },
        setStyles: function(t, e, n, i, o) {
            switch (e) {
                case "matrix":
                    e = "matrix", n = n.toArray();
                    break;

                case "vertices":
                    e = "commands", t.curved = o, t.closed = i, n = r.toArray(n, t.curved, t.closed);
            }
            t[e] = n;
        },
        Utils: r
    }), _.extend(o.prototype, Backbone.Events, {
        setSize: function(t, e) {
            return this.width = this.domElement.width = t, this.height = this.domElement.height = e,
                _.extend(this.domElement.style, {
                    width: this.width + "px",
                    height: this.height + "px"
                }), this;
        },
        add: function(t) {
            constructor = Object.getPrototypeOf(this).constructor;
            var e = t, n = this.elements, i = constructor.Group, r = constructor.Element, o = constructor.getStyles;
            return _.isArray(t) || (e = _.map(arguments, function(t) {
                return t;
            })), _.each(e, function(t) {
                var e;
                e = t instanceof Two.Group;
                var s = _.isNull(this.stage);
                if (_.isUndefined(t.id)) {
                    var a = this.count;
                    this.count++, t.id = a;
                }
                e ? (e = o.call(this, t), delete e.stroke, delete e.fill, delete e.opacity, delete e.cap,
                    delete e.join, delete e.miter, delete e.linewidth, e = new i(e), s && (this.stage = e,
                    this.stage.object = t, t.parent = this, t.unbind(Two.Events.change).bind(Two.Events.change, _.bind(this.update, this)))) : e = new r(o.call(this, t)),
                    n.push(e), s || this.stage.appendChild(e);
            }, this), this;
        },
        update: function(t, e, n, i, r, o) {
            var s = Object.getPrototypeOf(this).constructor, a = this.elements, u = a[t];
            switch (e) {
                case Two.Properties.hierarchy:
                    _.each(n, function(t) {
                        u.appendChild(a[t]);
                    });
                    break;

                case Two.Properties.demotion:
                    _.each(n, function(t) {
                        u.removeChild(a[t]), this.elements[t] = null;
                    }, this);
                    break;

                default:
                    s.setStyles.call(this, u, e, n, i, r, o);
            }
            return this;
        },
        render: function() {
            return _.isNull(this.stage) ? this : (this.ctx.clearRect(0, 0, this.width, this.height),
                this.stage.render(this.ctx), this);
        }
    });
}(), function() {
    function t(t, e) {
        var n = Math.min(t.top, e.top), i = Math.min(t.left, e.left), r = Math.max(t.right, e.right), o = Math.max(t.bottom, e.bottom);
        return {
            top: n,
            left: i,
            right: r,
            bottom: o,
            width: r - i,
            height: o - n,
            centroid: {
                x: -i,
                y: -n
            }
        };
    }
    var e = Two[Two.Types.canvas], n = Two.Matrix.Multiply, i = Two[Two.Types.canvas].Utils.toArray, r = Two.Utils.mod, o = function(t) {
        e.Group.call(this, t);
    };
    _.extend(o.prototype, e.Group.prototype, {
        appendChild: function() {
            return e.Group.prototype.appendChild.apply(this, arguments), this.updateMatrix(),
                this;
        },
        updateTexture: function(t) {
            return _.each(this.children, function(e) {
                e.updateTexture(t);
            }), this;
        },
        updateMatrix: function(t) {
            var e = t && t._matrix || this.parent && this.parent._matrix;
            return t = t && t._scale || this.parent && this.parent._scale, e ? (this._matrix = n(this.matrix, e),
                this._scale = this.scale * t, _.each(this.children, function(t) {
                t.updateMatrix(this);
            }, this), this) : this;
        },
        render: function(t, e) {
            _.each(this.children, function(n) {
                n.render(t, e);
            });
        }
    });
    var s = function(t) {
        e.Element.call(this, t);
    };
    _.extend(s.prototype, e.Element.prototype, {
        updateMatrix: function(t) {
            var e = t && t._matrix || this.parent && this.parent._matrix;
            return t = t && t._scale || this.parent && this.parent._scale, e ? (this._matrix = n(this.matrix, e),
                this._scale = this.scale * t, this) : this;
        },
        updateTexture: function(t) {
            return a.updateTexture(t, this), this;
        },
        render: function(t, e) {
            return this.visible && this.opacity && this.buffer ? (t.bindBuffer(t.ARRAY_BUFFER, this.textureCoordsBuffer),
                t.vertexAttribPointer(e.textureCoords, 2, t.FLOAT, !1, 0, 0), t.bindTexture(t.TEXTURE_2D, this.texture),
                t.uniformMatrix3fv(e.matrix, !1, this._matrix), t.bindBuffer(t.ARRAY_BUFFER, this.buffer),
                t.vertexAttribPointer(e.position, 2, t.FLOAT, !1, 0, 0), t.drawArrays(t.TRIANGLES, 0, 6),
                this) : this;
        }
    });
    var a = {
        canvas: document.createElement("canvas"),
        uv: new Two.Array([ 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1 ]),
        getBoundingClientRect: function(t, e, n) {
            var i = 1 / 0, r = -(1 / 0), o = 1 / 0, s = -(1 / 0);
            return _.each(t, function(t) {
                var e, a = t.x, u = t.y;
                o = Math.min(u, o), i = Math.min(a, i), r = Math.max(a, r), s = Math.max(u, s),
                n && (a = t.u.x, u = t.u.y, e = t.v.x, t = t.v.y, o = Math.min(u, t, o), i = Math.min(a, e, i),
                    r = Math.max(a, e, r), s = Math.max(u, t, s));
            }), _.isNumber(e) && (o -= e, i -= e, r += e, s += e), {
                top: o,
                left: i,
                right: r,
                bottom: s,
                width: r - i,
                height: s - o,
                centroid: {
                    x: -i,
                    y: -o
                }
            };
        },
        getTriangles: function(t) {
            var e = t.top, n = t.left, i = t.right;
            return t = t.bottom, new Two.Array([ n, e, i, e, n, t, n, t, i, e, i, t ]);
        },
        updateCanvas: function(t) {
            var e = t.commands, n = this.canvas, i = this.ctx, o = t._scale, s = t.stroke, a = t.linewidth * o, u = t.fill, c = t.opacity, l = t.cap, h = t.join, f = t.miter, d = t.curved, p = t.closed, m = e.length, g = m - 1;
            n.width = Math.max(Math.ceil(t.rect.width * o), 1), n.height = Math.max(Math.ceil(t.rect.height * o), 1),
                t = t.rect.centroid;
            var v = t.x * o, y = t.y * o;
            i.clearRect(0, 0, n.width, n.height), u && (i.fillStyle = u), s && (i.strokeStyle = s),
            a && (i.lineWidth = a), f && (i.miterLimit = f), h && (i.lineJoin = h), l && (i.lineCap = l),
            _.isNumber(c) && (i.globalAlpha = c), i.beginPath(), _.each(e, function(t, n) {
                var s = (t.x * o + v).toFixed(3), a = (t.y * o + y).toFixed(3);
                if (d) {
                    var u = p ? r(n - 1, m) : Math.max(n - 1, 0), c = p ? r(n + 1, m) : Math.min(n + 1, g), l = e[u], c = e[c], u = (l.v.x * o + v).toFixed(3), l = (l.v.y * o + y).toFixed(3), h = (t.u.x * o + v).toFixed(3), f = (t.u.y * o + y).toFixed(3);
                    0 >= n ? i.moveTo(s, a) : (i.bezierCurveTo(u, l, h, f, s, a), n >= g && p && (u = (t.v.x * o + v).toFixed(3),
                        l = (t.v.y * o + y).toFixed(3), h = (c.u.x * o + v).toFixed(3), f = (c.u.y * o + y).toFixed(3),
                        s = (c.x * o + v).toFixed(3), a = (c.y * o + y).toFixed(3), i.bezierCurveTo(u, l, h, f, s, a)));
                } else 0 >= n ? i.moveTo(s, a) : i.lineTo(s, a);
            }), p && !d && i.closePath(), i.fill(), i.stroke();
        },
        updateTexture: function(t, e) {
            this.updateCanvas(e), e.texture && t.deleteTexture(e.texture), t.bindBuffer(t.ARRAY_BUFFER, e.textureCoordsBuffer),
                e.texture = t.createTexture(), t.bindTexture(t.TEXTURE_2D, e.texture), t.texParameteri(t.TEXTURE_2D, t.TEXTURE_WRAP_S, t.CLAMP_TO_EDGE),
                t.texParameteri(t.TEXTURE_2D, t.TEXTURE_WRAP_T, t.CLAMP_TO_EDGE), t.texParameteri(t.TEXTURE_2D, t.TEXTURE_MIN_FILTER, t.LINEAR),
            0 >= this.canvas.width || 0 >= this.canvas.height || t.texImage2D(t.TEXTURE_2D, 0, t.RGBA, t.RGBA, t.UNSIGNED_BYTE, this.canvas);
        },
        updateBuffer: function(t, e, n) {
            _.isObject(e.buffer) && t.deleteBuffer(e.buffer), e.buffer = t.createBuffer(), t.bindBuffer(t.ARRAY_BUFFER, e.buffer),
                t.enableVertexAttribArray(n.position), t.bufferData(t.ARRAY_BUFFER, e.triangles, t.STATIC_DRAW),
            _.isObject(e.textureCoordsBuffer) && t.deleteBuffer(e.textureCoordsBuffer), e.textureCoordsBuffer = t.createBuffer(),
                t.bindBuffer(t.ARRAY_BUFFER, e.textureCoordsBuffer), t.enableVertexAttribArray(n.textureCoords),
                t.bufferData(t.ARRAY_BUFFER, this.uv, t.STATIC_DRAW);
        },
        program: {
            create: function(t, e) {
                var n = t.createProgram();
                if (_.each(e, function(e) {
                        t.attachShader(n, e);
                    }), t.linkProgram(n), !t.getProgramParameter(n, t.LINK_STATUS)) throw error = t.getProgramInfoLog(n),
                    t.deleteProgram(n), new Two.Utils.Error("unable to link program: " + error);
                return n;
            }
        },
        shaders: {
            create: function(t, e, n) {
                if (n = t.createShader(t[n]), t.shaderSource(n, e), t.compileShader(n), !t.getShaderParameter(n, t.COMPILE_STATUS)) throw e = t.getShaderInfoLog(n),
                    t.deleteShader(n), new Two.Utils.Error("unable to compile shader " + n + ": " + e);
                return n;
            },
            types: {
                vertex: "VERTEX_SHADER",
                fragment: "FRAGMENT_SHADER"
            },
            vertex: "attribute vec2 a_position;\nattribute vec2 a_textureCoords;\n\nuniform mat3 u_matrix;\nuniform vec2 u_resolution;\n\nvarying vec2 v_textureCoords;\n\nvoid main() {\n   vec2 projected = (u_matrix * vec3(a_position, 1)).xy;\n   vec2 normal = projected / u_resolution;\n   vec2 clipspace = (normal * 2.0) - 1.0;\n\n   gl_Position = vec4(clipspace * vec2(1.0, -1.0), 0.0, 1.0);\n   v_textureCoords = a_textureCoords;\n}",
            fragment: "precision mediump float;\n\nuniform sampler2D u_image;\nvarying vec2 v_textureCoords;\n\nvoid main() {\n  gl_FragColor = texture2D(u_image, v_textureCoords);\n}"
        }
    };
    a.ctx = a.canvas.getContext("2d");
    var u = Two[Two.Types.webgl] = function(t) {
        if (this.count = 0, this.domElement = document.createElement("canvas"), this.elements = [],
                this.stage = null, t = _.defaults(t || {}, {
                antialias: !1,
                alpha: !0,
                premultipliedAlpha: !0,
                stencil: !0,
                preserveDrawingBuffer: !1
            }), t = this.ctx = this.domElement.getContext("webgl", t) || this.domElement.getContext("experimental-webgl", t),
                !this.ctx) throw new Two.Utils.Error("unable to create a webgl context. Try using another renderer.");
        var e = a.shaders.create(t, a.shaders.vertex, a.shaders.types.vertex), n = a.shaders.create(t, a.shaders.fragment, a.shaders.types.fragment);
        this.program = a.program.create(t, [ e, n ]), t.useProgram(this.program), this.program.position = t.getAttribLocation(this.program, "a_position"),
            this.program.matrix = t.getUniformLocation(this.program, "u_matrix"), this.program.textureCoords = t.getAttribLocation(this.program, "a_textureCoords"),
            t.disable(t.DEPTH_TEST), t.enable(t.BLEND), t.blendEquationSeparate(t.FUNC_ADD, t.FUNC_ADD),
            t.blendFuncSeparate(t.SRC_ALPHA, t.ONE_MINUS_SRC_ALPHA, t.ONE, t.ONE_MINUS_SRC_ALPHA);
    };
    _.extend(u, {
        Group: o,
        Element: s,
        getStyles: function(t) {
            var e = {}, n = t.id, r = t._matrix, o = t.stroke, u = t.linewidth, c = t.fill, l = t.opacity, h = t.visible, f = t.cap, d = t.join, p = t.miter, m = t.curved, g = t.closed, v = t.vertices;
            return n && (e.id = n), _.isObject(r) && (e.matrix = e._matrix = r.toArray(!0),
                e.scale = e._scale = 1), o && (e.stroke = o), c && (e.fill = c), _.isNumber(l) && (e.opacity = l),
            f && (e.cap = f), d && (e.join = d), p && (e.miter = p), u && (e.linewidth = u),
            v && (e.vertices = i(v, m, g), e.commands = e.vertices, e.rect = a.getBoundingClientRect(e.commands, e.linewidth, e.curved),
                e.triangles = a.getTriangles(e.rect)), e.visible = !!h, e.curved = !!m, e.closed = !!g,
            t instanceof Two.Polygon && (a.updateBuffer(this.ctx, e, this.program), s.prototype.updateTexture.call(e, this.ctx)),
                e;
        },
        setStyles: function(e, n, r, o, s, u) {
            var c = !1;
            /matrix/.test(n) ? (e[n] = r.toArray(!0), _.isNumber(o) && (c = e.scale !== o, e.scale = o),
                e.updateMatrix()) : /(stroke|fill|opacity|cap|join|miter|linewidth)/.test(n) ? (e[n] = r,
                e.rect = t(a.getBoundingClientRect(e.commands, e.linewidth, e.curved), e.rect),
                e.triangles = a.getTriangles(e.rect), a.updateBuffer(this.ctx, e, this.program),
                c = !0) : "vertices" === n ? (_.isUndefined(o) || (e.closed = o), _.isUndefined(s) || (e.curved = s),
                u ? e.commands = i(r, e.curved, e.closed) : (e.vertices = i(r, e.curved, e.closed),
                    e.commands = e.vertices), e.rect = t(a.getBoundingClientRect(e.vertices, e.linewidth, e.curved), e.rect),
                e.triangles = a.getTriangles(e.rect), a.updateBuffer(this.ctx, e, this.program),
                c = !0) : e[n] = r, c && e.updateTexture(this.ctx);
        }
    }), _.extend(u.prototype, Backbone.Events, e.prototype, {
        setSize: function(t, n) {
            e.prototype.setSize.apply(this, arguments), this.ctx.viewport(0, 0, t, n);
            var i = this.ctx.getUniformLocation(this.program, "u_resolution");
            return this.ctx.uniform2f(i, t, n), this;
        },
        render: function() {
            return _.isNull(this.stage) ? this : (this.stage.render(this.ctx, this.program),
                this);
        }
    });
}(), function() {
    var t = Two.Shape = function(e) {
        this._matrix = new Two.Matrix();
        var n = _.debounce(_.bind(function() {
            var t = this._matrix.identity().translate(this.translation.x, this.translation.y).scale(this.scale).rotate(this.rotation);
            this.trigger(Two.Events.change, this.id, "matrix", t, this.scale);
        }, this), 0);
        return this._rotation = 0, Object.defineProperty(this, "rotation", {
            get: function() {
                return this._rotation;
            },
            set: function(t) {
                this._rotation = t, n();
            }
        }), this._scale = "scale", Object.defineProperty(this, "scale", {
            get: function() {
                return this._scale;
            },
            set: function(t) {
                this._scale = t, n();
            }
        }), this.translation = new Two.Vector(), this.rotation = 0, this.scale = 1, this.translation.bind(Two.Events.change, n),
            e ? this : (t.MakeGetterSetter(this, t.Properties), this.fill = "#fff", this.stroke = "#000",
                this.opacity = this.linewidth = 1, this.visible = !0, this.join = this.cap = "round",
                void (this.miter = 1));
    };
    _.extend(t, {
        Properties: "fill stroke linewidth opacity visible cap join miter".split(" "),
        MakeGetterSetter: function(t, e) {
            _.isArray(e) || (e = [ e ]), _.each(e, function(e) {
                var n = "_" + e;
                Object.defineProperty(t, e, {
                    get: function() {
                        return this[n];
                    },
                    set: function(t) {
                        this[n] = t, this.trigger(Two.Events.change, this.id, e, t, this);
                    }
                });
            });
        }
    }), _.extend(t.prototype, Backbone.Events, {
        addTo: function(t) {
            return t.add(this), this;
        },
        noFill: function() {
            return this.fill = "transparent", this;
        },
        noStroke: function() {
            return this.stroke = "transparent", this;
        },
        clone: function() {
            var e = new t();
            return e.translation.copy(this.translation), _.each(t.Properties, function(t) {
                e[t] = this[t];
            }, this), this;
        }
    });
}(), function() {
    var t = Two.Group = function() {
        Two.Shape.call(this, !0), delete this.stroke, delete this.fill, delete this.linewidth,
            delete this.opacity, delete this.cap, delete this.join, delete this.miter, t.MakeGetterSetter(this, Two.Shape.Properties),
            this.children = {};
    };
    _.extend(t, {
        MakeGetterSetter: function(t, e) {
            _.isArray(e) || (e = [ e ]), _.each(e, function(e) {
                var n = "_" + e;
                Object.defineProperty(t, e, {
                    get: function() {
                        return this[n];
                    },
                    set: function(t) {
                        this[n] = t, _.each(this.children, function(n) {
                            n[e] = t;
                        });
                    }
                });
            });
        }
    }), _.extend(t.prototype, Two.Shape.prototype, {
        clone: function(e) {
            e = e || this.parent;
            var n = _.map(this.children, function(t) {
                return t.clone(e);
            }), i = new t();
            return e.add(i), i.add(n), i.translation.copy(this.translation), i.rotation = this.rotation,
                i.scale = this.scale, i;
        },
        center: function() {
            var t = this.getBoundingClientRect();
            return t.centroid = {
                x: t.left + t.width / 2,
                y: t.top + t.height / 2
            }, _.each(this.children, function(e) {
                e.translation.subSelf(t.centroid);
            }), this.translation.copy(t.centroid), this;
        },
        add: function(t) {
            var e = t, n = this.children, i = this.parent, r = [];
            _.isArray(t) || (e = _.toArray(arguments));
            var o = _.bind(function(t, e, n, i, r, o) {
                this.trigger(Two.Events.change, t, e, n, i, r, o);
            }, this);
            return _.each(e, function(t) {
                var e = t.id, s = t.parent;
                _.isUndefined(e) && (i.add(t), e = t.id), _.isUndefined(n[e]) && (s && delete s.children[e],
                    n[e] = t, t.parent = this, t.unbind(Two.Events.change).bind(Two.Events.change, o),
                    r.push(e));
            }, this), 0 < r.length && this.trigger(Two.Events.change, this.id, Two.Properties.hierarchy, r),
                this;
        },
        remove: function(t) {
            var e = t, n = this.children, i = this.parent, r = [];
            return 0 >= arguments.length && i ? (i.remove(this), this) : (_.isArray(t) || (e = _.toArray(arguments)),
                _.each(e, function(t) {
                    var e = t.id;
                    e in n && (delete n[e], t.unbind(Two.Events.change), r.push(e));
                }), 0 < r.length && this.trigger(Two.Events.change, this.id, Two.Properties.demotion, r),
                this);
        },
        getBoundingClientRect: function() {
            var t = 1 / 0, e = -(1 / 0), n = 1 / 0, i = -(1 / 0);
            _.each(this.children, function(r) {
                r = r.getBoundingClientRect(), n && t && e && i && (n = Math.min(r.top, n), t = Math.min(r.left, t),
                    e = Math.max(r.right, e), i = Math.max(r.bottom, i));
            }, this);
            var r = this._matrix.multiply(t, n, 1), o = this._matrix.multiply(e, i, 1);
            return {
                top: r.y,
                left: r.x,
                right: o.x,
                bottom: o.y,
                width: o.x - r.x,
                height: o.y - r.y
            };
        },
        noFill: function() {
            return _.each(this.children, function(t) {
                t.noFill();
            }), this;
        },
        noStroke: function() {
            return _.each(this.children, function(t) {
                t.noStroke();
            }), this;
        }
    });
}(), function() {
    var t = Math.min, e = Math.max, n = Math.round, i = Two.Polygon = function(i, r, o) {
        Two.Shape.call(this), r = !!r, o = !!o;
        var s = 0, a = 1, u = !1, c = i.slice(0), l = _.debounce(_.bind(function() {
            var t, e;
            if (u) for (t = this.vertices.length, t -= 1, e = n(s * t), t = n(a * t), c.length = 0; t + 1 > e; e++) {
                var i = this.vertices[e];
                c.push(new Two.Vector(i.x, i.y));
            }
            this.trigger(Two.Events.change, this.id, "vertices", c, r, o, u), u = !1;
        }, this), 0);
        Object.defineProperty(this, "closed", {
            get: function() {
                return r;
            },
            set: function(t) {
                r = !!t, l();
            }
        }), Object.defineProperty(this, "curved", {
            get: function() {
                return o;
            },
            set: function(t) {
                o = !!t, l();
            }
        }), Object.defineProperty(this, "beginning", {
            get: function() {
                return s;
            },
            set: function(n) {
                s = t(e(n, 0), 1), u = !0, l();
            }
        }), Object.defineProperty(this, "ending", {
            get: function() {
                return a;
            },
            set: function(n) {
                a = t(e(n, 0), 1), u = !0, l();
            }
        }), this.vertices = i.slice(0), _.each(this.vertices, function(t) {
            t.bind(Two.Events.change, l);
        }, this), l();
    };
    _.extend(i.prototype, Two.Shape.prototype, {
        clone: function() {
            var t = _.map(this.vertices, function(t) {
                return new Two.Vector(t.x, t.y);
            }), e = new i(t, this.closed, this.curved);
            return _.each(Two.Shape.Properties, function(t) {
                e[t] = this[t];
            }, this), e.translation.copy(this.translation), e.rotation = this.rotation, e.scale = this.scale,
                e;
        },
        center: function() {
            var t = this.getBoundingClientRect();
            return t.centroid = {
                x: t.left + t.width / 2,
                y: t.top + t.height / 2
            }, _.each(this.vertices, function(e) {
                e.subSelf(t.centroid);
            }), this.translation.addSelf(t.centroid), this;
        },
        remove: function() {
            return this.parent ? (this.parent.remove(this), this) : this;
        },
        getBoundingClientRect: function() {
            var t = this.linewidth, e = 1 / 0, n = -(1 / 0), i = 1 / 0, r = -(1 / 0);
            _.each(this.vertices, function(t) {
                var o = t.x;
                t = t.y, i = Math.min(t, i), e = Math.min(o, e), n = Math.max(o, n), r = Math.max(t, r);
            });
            var i = i - t, e = e - t, n = n + t, r = r + t, t = this._matrix.multiply(e, i, 1), o = this._matrix.multiply(n, r, 1);
            return {
                top: t.y,
                left: t.x,
                right: o.x,
                bottom: o.y,
                width: o.x - t.x,
                height: o.y - t.y
            };
        }
    });
}(), !function(t) {
    "object" == typeof exports ? module.exports = t() : "function" == typeof define && define.amd ? define(t) : "undefined" != typeof window ? window.IconicJS = t() : "undefined" != typeof global ? global.IconicJS = t() : "undefined" != typeof self && (self.IconicJS = t());
}(function() {
    return function t(e, n, i) {
        function r(s, a) {
            if (!n[s]) {
                if (!e[s]) {
                    var u = "function" == typeof require && require;
                    if (!a && u) return u(s, !0);
                    if (o) return o(s, !0);
                    throw new Error("Cannot find module '" + s + "'");
                }
                var c = n[s] = {
                    exports: {}
                };
                e[s][0].call(c.exports, function(t) {
                    var n = e[s][1][t];
                    return r(n ? n : t);
                }, c, c.exports, t, e, n, i);
            }
            return n[s].exports;
        }
        for (var o = "function" == typeof require && require, s = 0; s < i.length; s++) r(i[s]);
        return r;
    }({
        1: [ function(t, e) {
            var n = (t("./modules/polyfills"), t("./modules/svgInjector")), i = t("./modules/extend"), r = t("./modules/responsive"), o = t("./modules/position"), s = t("./modules/container"), a = t("./modules/log"), u = {}, c = window.iconicSmartIconApis = {}, l = ("file:" === window.location.protocol,
                0), h = function(t, e, r) {
                e = i({}, u, e || {});
                var o = {
                    evalScripts: e.evalScripts,
                    pngFallback: e.pngFallback
                };
                o.each = function(t) {
                    if (t) if (t instanceof SVGSVGElement) {
                        var n = t.getAttribute("data-icon");
                        if (n && c[n]) {
                            var i = c[n](t);
                            for (var r in i) t[r] = i[r];
                        }
                        /iconic-bg-/.test(t.getAttribute("class")) && s.addBackground(t), f(t), l++, e && e.each && "function" == typeof e.each && e.each(t);
                    } else "string" == typeof t && a.debug(t);
                };
                var h = document.querySelectorAll(t);
                n(h, o, r);
            }, f = function(t) {
                var e = [];
                t ? "object" == typeof t ? e.push(t) : "string" == typeof t && (e = document.querySelectorAll(t)) : e = document.querySelectorAll("svg.iconic"),
                    Array.prototype.forEach.call(e, function(t) {
                        t instanceof SVGSVGElement && (t.update && t.update(), r.refresh(t), o.refresh(t));
                    });
            }, d = function() {
                u.debug && console.time && console.time("autoInjectSelector - " + u.autoInjectSelector);
                var t = l;
                h(u.autoInjectSelector, {}, function() {
                    if (u.debug && console.timeEnd && console.timeEnd("autoInjectSelector - " + u.autoInjectSelector),
                            a.debug("AutoInjected: " + (l - t)), r.refreshAll(), u.autoInjectDone && "function" == typeof u.autoInjectDone) {
                        var e = l - t;
                        u.autoInjectDone(e);
                    }
                });
            }, p = function(t) {
                t && "" !== t && "complete" !== document.readyState ? document.addEventListener("DOMContentLoaded", d) : document.removeEventListener("DOMContentLoaded", d);
            }, m = function(t) {
                return t = t || {}, i(u, t), p(u.autoInjectSelector), a.enableDebug(u.debug), window._Iconic ? window._Iconic : {
                    inject: h,
                    update: f,
                    smartIconApis: c,
                    svgInjectedCount: l
                };
            };
            e.exports = m, window._Iconic = new m({
                autoInjectSelector: "img.iconic",
                evalScripts: "once",
                pngFallback: !1,
                each: null,
                autoInjectDone: null,
                debug: !1
            });
        }, {
            "./modules/container": 2,
            "./modules/extend": 3,
            "./modules/log": 4,
            "./modules/polyfills": 5,
            "./modules/position": 6,
            "./modules/responsive": 7,
            "./modules/svgInjector": 8
        } ],
        2: [ function(t, e) {
            var n = function(t) {
                var e = t.getAttribute("class").split(" "), n = -1 !== e.indexOf("iconic-fluid"), i = [], r = [ "iconic-bg" ];
                Array.prototype.forEach.call(e, function(t) {
                    switch (t) {
                        case "iconic-sm":
                        case "iconic-md":
                        case "iconic-lg":
                            i.push(t), n || r.push(t.replace(/-/, "-bg-"));
                            break;

                        case "iconic-fluid":
                            i.push(t), r.push(t.replace(/-/, "-bg-"));
                            break;

                        case "iconic-bg-circle":
                        case "iconic-bg-rounded-rect":
                        case "iconic-bg-badge":
                            r.push(t);
                            break;

                        default:
                            i.push(t);
                    }
                }), t.setAttribute("class", i.join(" "));
                var o = t.parentNode, s = Array.prototype.indexOf.call(o.childNodes, t), a = document.createElement("span");
                a.setAttribute("class", r.join(" ")), a.appendChild(t), o.insertBefore(a, o.childNodes[s]);
            };
            e.exports = {
                addBackground: n
            };
        }, {} ],
        3: [ function(t, e) {
            e.exports = function(t) {
                return Array.prototype.forEach.call(Array.prototype.slice.call(arguments, 1), function(e) {
                    if (e) for (var n in e) e.hasOwnProperty(n) && (t[n] = e[n]);
                }), t;
            };
        }, {} ],
        4: [ function(t, e) {
            var n = !1, i = function(t) {
                console && console.log && console.log(t);
            }, r = function(t) {
                i("Iconic INFO: " + t);
            }, o = function(t) {
                i("Iconic WARNING: " + t);
            }, s = function(t) {
                n && i("Iconic DEBUG: " + t);
            }, a = function(t) {
                n = t;
            };
            e.exports = {
                info: r,
                warn: o,
                debug: s,
                enableDebug: a
            };
        }, {} ],
        5: [ function() {
            Array.prototype.forEach || (Array.prototype.forEach = function(t, e) {
                "use strict";
                var n, i;
                for (n = 0, i = this.length; i > n; ++n) n in this && t.call(e, this[n], n, this);
            });
        }, {} ],
        6: [ function(t, e) {
            var n = function(t) {
                var e = t.getAttribute("data-position");
                if (e && "" !== e) {
                    var n, i, r, o, s, a, u, c = t.getAttribute("width"), l = t.getAttribute("height"), h = e.split("-"), f = t.querySelectorAll("g.iconic-container");
                    Array.prototype.forEach.call(f, function(t) {
                        if (n = t.getAttribute("data-width"), i = t.getAttribute("data-height"), n !== c || i !== l) {
                            if (r = t.getAttribute("transform"), o = 1, r) {
                                var e = r.match(/scale\((\d)/);
                                o = e && e[1] ? e[1] : 1;
                            }
                            s = Math.floor((c / o - n) / 2), a = Math.floor((l / o - i) / 2), Array.prototype.forEach.call(h, function(t) {
                                switch (t) {
                                    case "top":
                                        a = 0;
                                        break;

                                    case "bottom":
                                        a = l / o - i;
                                        break;

                                    case "left":
                                        s = 0;
                                        break;

                                    case "right":
                                        s = c / o - n;
                                        break;

                                    case "center":
                                        break;

                                    default:
                                        console.log("Unknown position: " + t);
                                }
                            }), u = 0 === a ? s : s + " " + a, u = "translate(" + u + ")", r ? /translate/.test(r) ? r = r.replace(/translate\(.*?\)/, u) : r += " " + u : r = u,
                                t.setAttribute("transform", r);
                        }
                    });
                }
            };
            e.exports = {
                refresh: n
            };
        }, {} ],
        7: [ function(t, e) {
            var n = /(iconic-sm\b|iconic-md\b|iconic-lg\b)/, i = function(t, e) {
                var n = "undefined" != typeof window.getComputedStyle && window.getComputedStyle(t, null).getPropertyValue(e);
                return !n && t.currentStyle && (n = t.currentStyle[e.replace(/([a-z])\-([a-z])/, function(t, e, n) {
                        return e + n.toUpperCase();
                    })] || t.currentStyle[e]), n;
            }, r = function(t) {
                var e = t.style.display;
                t.style.display = "block";
                var n = parseFloat(i(t, "width").slice(0, -2)), r = parseFloat(i(t, "height").slice(0, -2));
                return t.style.display = e, {
                    width: n,
                    height: r
                };
            }, o = function() {
                var t = "/* Iconic Responsive Support Styles */\n.iconic-property-fill, .iconic-property-text {stroke: none !important;}\n.iconic-property-stroke {fill: none !important;}\nsvg.iconic.iconic-fluid {height:100% !important;width:100% !important;}\nsvg.iconic.iconic-sm:not(.iconic-size-md):not(.iconic-size-lg), svg.iconic.iconic-size-sm{width:16px;height:16px;}\nsvg.iconic.iconic-md:not(.iconic-size-sm):not(.iconic-size-lg), svg.iconic.iconic-size-md{width:32px;height:32px;}\nsvg.iconic.iconic-lg:not(.iconic-size-sm):not(.iconic-size-md), svg.iconic.iconic-size-lg{width:128px;height:128px;}\nsvg.iconic-sm > g.iconic-md, svg.iconic-sm > g.iconic-lg, svg.iconic-md > g.iconic-sm, svg.iconic-md > g.iconic-lg, svg.iconic-lg > g.iconic-sm, svg.iconic-lg > g.iconic-md {display: none;}\nsvg.iconic.iconic-icon-sm > g.iconic-lg, svg.iconic.iconic-icon-md > g.iconic-lg {display:none;}\nsvg.iconic-sm:not(.iconic-icon-md):not(.iconic-icon-lg) > g.iconic-sm, svg.iconic-md.iconic-icon-sm > g.iconic-sm, svg.iconic-lg.iconic-icon-sm > g.iconic-sm {display:inline;}\nsvg.iconic-md:not(.iconic-icon-sm):not(.iconic-icon-lg) > g.iconic-md, svg.iconic-sm.iconic-icon-md > g.iconic-md, svg.iconic-lg.iconic-icon-md > g.iconic-md {display:inline;}\nsvg.iconic-lg:not(.iconic-icon-sm):not(.iconic-icon-md) > g.iconic-lg, svg.iconic-sm.iconic-icon-lg > g.iconic-lg, svg.iconic-md.iconic-icon-lg > g.iconic-lg {display:inline;}";
                navigator && navigator.userAgent && /MSIE 10\.0/.test(navigator.userAgent) && (t += "svg.iconic{zoom:1.0001;}");
                var e = document.createElement("style");
                e.id = "iconic-responsive-css", e.type = "text/css", e.styleSheet ? e.styleSheet.cssText = t : e.appendChild(document.createTextNode(t)),
                    (document.head || document.getElementsByTagName("head")[0]).appendChild(e);
            }, s = function(t) {
                if (/iconic-fluid/.test(t.getAttribute("class"))) {
                    var e, i = r(t), o = t.viewBox.baseVal.width / t.viewBox.baseVal.height;
                    e = 1 === o ? Math.min(i.width, i.height) : 1 > o ? i.width : i.height;
                    var s;
                    s = 32 > e ? "iconic-sm" : e >= 32 && 128 > e ? "iconic-md" : "iconic-lg";
                    var a = t.getAttribute("class"), u = n.test(a) ? a.replace(n, s) : a + " " + s;
                    t.setAttribute("class", u);
                }
            }, a = function() {
                var t = document.querySelectorAll(".iconic-injected-svg.iconic-fluid");
                Array.prototype.forEach.call(t, function(t) {
                    s(t);
                });
            };
            document.addEventListener("DOMContentLoaded", function() {
                o();
            }), window.addEventListener("resize", function() {
                a();
            }), e.exports = {
                refresh: s,
                refreshAll: a
            };
        }, {} ],
        8: [ function(t, e) {
            var n = "file:" === window.location.protocol, i = document.implementation.hasFeature("http://www.w3.org/TR/SVG11/feature#BasicStructure", "1.1"), r = {}, o = 0, s = [], a = {}, u = function(t) {
                return t.cloneNode(!0);
            }, c = function(t, e) {
                s[t] = s[t] || [], s[t].push(e);
            }, l = function(t) {
                for (var e = 0, n = s[t].length; n > e; e++) !function(e) {
                    setTimeout(function() {
                        s[t][e](u(r[t]));
                    }, 0);
                }(e);
            }, h = function(t, e) {
                if (void 0 !== r[t]) r[t] instanceof SVGSVGElement ? e(r[t].cloneNode(!0)) : c(t, e); else {
                    if (!window.XMLHttpRequest) return e("Browser does not support XMLHttpRequest"),
                        !1;
                    r[t] = {}, c(t, e);
                    var i = new XMLHttpRequest();
                    i.onreadystatechange = function() {
                        if (4 === i.readyState) {
                            if (404 === i.status || null === i.responseXML) return e("Unable to load SVG file: " + t),
                            n && e("Note: SVG injection ajax calls do not work locally without adjusting security setting in your browser. Or consider using a local webserver."),
                                e(), !1;
                            if (!(200 === i.status || n && 0 === i.status)) return e("There was a problem injecting the SVG: " + i.status + " " + i.statusText),
                                !1;
                            if (i.responseXML instanceof Document) r[t] = i.responseXML.documentElement; else if (DOMParser && DOMParser instanceof Function) {
                                var o;
                                try {
                                    var s = new DOMParser();
                                    o = s.parseFromString(i.responseText, "text/xml");
                                } catch (a) {
                                    o = void 0;
                                }
                                if (!o || o.getElementsByTagName("parsererror").length) return e("Unable to parse SVG file: " + t),
                                    !1;
                                r[t] = o.documentElement;
                            }
                            l(t);
                        }
                    }, i.open("GET", t), i.overrideMimeType && i.overrideMimeType("text/xml"), i.send();
                }
            }, f = function(t, e, n, r) {
                var s = t.getAttribute("src") || t.getAttribute("data-src");
                return /svg$/i.test(s) ? i ? (t.setAttribute("src", ""), void h(s, function(n) {
                    if (void 0 === n || "string" == typeof n) return r(n), !1;
                    var i = t.getAttribute("id");
                    i && n.setAttribute("id", i);
                    var u = t.getAttribute("title");
                    u && n.setAttribute("title", u);
                    var c = t.getAttribute("class");
                    if (c) {
                        var l = [ n.getAttribute("class"), "iconic-injected-svg", c ].join(" ");
                        n.setAttribute("class", l);
                    }
                    var h = t.getAttribute("style");
                    h && n.setAttribute("style", h);
                    var f = [].filter.call(t.attributes, function(t) {
                        return /^data-\w[\w\-]*$/.test(t.name);
                    });
                    Array.prototype.forEach.call(f, function(t) {
                        t.name && t.value && n.setAttribute(t.name, t.value);
                    });
                    for (var d, p = n.querySelectorAll("defs clipPath[id]"), m = 0, g = p.length; g > m; m++) {
                        d = p[m].id + "-" + o;
                        for (var v = n.querySelectorAll('[clip-path*="' + p[m].id + '"]'), y = 0, b = v.length; b > y; y++) v[y].setAttribute("clip-path", "url(#" + d + ")");
                        p[m].id = d;
                    }
                    n.removeAttribute("xmlns:a");
                    for (var x, w, _ = n.querySelectorAll("script"), k = [], C = 0, E = _.length; E > C; C++) w = _[C].getAttribute("type"),
                    w && "application/ecmascript" !== w && "application/javascript" !== w || (x = _[C].innerText || _[C].textContent,
                        k.push(x), n.removeChild(_[C]));
                    if (k.length > 0 && ("always" === e || "once" === e && !a[s])) {
                        for (var T = 0, A = k.length; A > T; T++) new Function(k[T])(window);
                        a[s] = !0;
                    }
                    t.parentNode.replaceChild(n, t), o++, r(n);
                })) : void (n ? (t.setAttribute("src", n + "/" + s.split("/").pop().replace(".svg", ".png")),
                    r(null)) : r("This browser does not support SVG and no PNG fallback was defined.")) : void r("Attempted to inject a non-svg file: " + s);
            }, d = function(t, e, n) {
                e = e || {};
                var i = e.evalScripts || "always", r = e.pngFallback || !1, o = e.each;
                if (void 0 !== t.length) {
                    var s = 0;
                    Array.prototype.forEach.call(t, function(e) {
                        f(e, i, r, function(e) {
                            o && "function" == typeof o && o(e), n && t.length === ++s && n(s);
                        });
                    });
                } else null !== t ? f(t, i, r, function(t) {
                    o && "function" == typeof o && o(t), n && n(1);
                }) : n && n(0);
            };
            e.exports = d;
        }, {} ]
    }, {}, [ 1 ])(1);
}), "function" == typeof Event && function() {
    function t(t) {
        return t.toLowerCase().replace(/-(.)/g, function(t, e) {
            return e.toUpperCase();
        });
    }
    function e(t) {
        return 4 == t.length ? [ "#", t.substring(1, 2), t.substring(1, 2), t.substring(2, 3), t.substring(2, 3), t.substring(3, 4), t.substring(3, 4) ].join("") : t;
    }
    function n(t) {
        var e = t.toString(16);
        return 1 == e.length ? "0" + e : e;
    }
    function i(t, e, n) {
        return (null == e || null == n) && (null == n ? n = t.height / t.width * e : null == e && (e = t.width / t.height * n)),
        {
            width: e,
            height: n
        };
    }
    function r(t, e) {
        return "number" == typeof t.from ? t.from + (t.to - t.from) * e : t instanceof c.Color || t instanceof c.Number ? t.at(e) : 1 > e ? t.from : t.to;
    }
    function o(t) {
        for (var e = 0, n = t.length, i = ""; n > e; e++) i += t[e][0], null != t[e][1] && (i += t[e][1],
        null != t[e][2] && (i += " ", i += t[e][2], null != t[e][3] && (i += " ", i += t[e][3],
            i += " ", i += t[e][4], null != t[e][5] && (i += " ", i += t[e][5], i += " ", i += t[e][6],
        null != t[e][7] && (i += " ", i += t[e][7])))));
        return i + " ";
    }
    function s(t) {
        t.x2 = t.x + t.width, t.y2 = t.y + t.height, t.cx = t.x + t.width / 2, t.cy = t.y + t.height / 2;
    }
    function a(t) {
        if (t.matrix) {
            var e = t.matrix.replace(/\s/g, "").split(",");
            6 == e.length && (t.a = parseFloat(e[0]), t.b = parseFloat(e[1]), t.c = parseFloat(e[2]),
                t.d = parseFloat(e[3]), t.e = parseFloat(e[4]), t.f = parseFloat(e[5]));
        }
        return t;
    }
    function u(t) {
        var e = t.toString().match(c.regex.reference);
        return e ? e[1] : void 0;
    }
    var c = this.SVG = function(t) {
        return c.supported ? (t = new c.Doc(t), c.parser || c.prepare(t), t) : void 0;
    };
    if (c.ns = "http://www.w3.org/2000/svg", c.xmlns = "http://www.w3.org/2000/xmlns/",
            c.xlink = "http://www.w3.org/1999/xlink", c.did = 1e3, c.eid = function(t) {
            return "Svgjs" + t.charAt(0).toUpperCase() + t.slice(1) + c.did++;
        }, c.create = function(t) {
            var e = document.createElementNS(this.ns, t);
            return e.setAttribute("id", this.eid(t)), e;
        }, c.extend = function() {
            var t, e, n, i;
            for (t = [].slice.call(arguments), e = t.pop(), i = t.length - 1; i >= 0; i--) if (t[i]) for (n in e) t[i].prototype[n] = e[n];
            c.Set && c.Set.inherit && c.Set.inherit();
        }, c.prepare = function(t) {
            var e = document.getElementsByTagName("body")[0], n = (e ? new c.Doc(e) : t.nested()).size(2, 0), i = c.create("path");
            n.node.appendChild(i), c.parser = {
                body: e || t.parent,
                draw: n.style("opacity:0;position:fixed;left:100%;top:100%;overflow:hidden"),
                poly: n.polyline().node,
                path: i
            };
        }, c.supported = function() {
            return !!document.createElementNS && !!document.createElementNS(c.ns, "svg").createSVGRect;
        }(), !c.supported) return !1;
    c.get = function(t) {
        var e = document.getElementById(u(t) || t);
        return e ? e.instance : void 0;
    }, c.invent = function(t) {
        var e = "function" == typeof t.create ? t.create : function() {
            this.constructor.call(this, c.create(t.create));
        };
        return t.inherit && (e.prototype = new t.inherit()), t.extend && c.extend(e, t.extend),
        t.construct && c.extend(t.parent || c.Container, t.construct), e;
    }, c.regex = {
        unit: /^(-?[\d\.]+)([a-z%]{0,2})$/,
        hex: /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i,
        rgb: /rgb\((\d+),(\d+),(\d+)\)/,
        reference: /#([a-z0-9\-_]+)/i,
        isHex: /^#[a-f0-9]{3,6}$/i,
        isRgb: /^rgb\(/,
        isCss: /[^:]+:[^;]+;?/,
        isBlank: /^(\s+)?$/,
        isNumber: /^-?[\d\.]+$/,
        isPercent: /^-?[\d\.]+%$/,
        isImage: /\.(jpg|jpeg|png|gif)(\?[^=]+.*)?/i,
        isEvent: /^[\w]+:[\w]+$/
    }, c.defaults = {
        matrix: "1 0 0 1 0 0",
        attrs: {
            "fill-opacity": 1,
            "stroke-opacity": 1,
            "stroke-width": 0,
            "stroke-linejoin": "miter",
            "stroke-linecap": "butt",
            fill: "#000000",
            stroke: "#000000",
            opacity: 1,
            x: 0,
            y: 0,
            cx: 0,
            cy: 0,
            width: 0,
            height: 0,
            r: 0,
            rx: 0,
            ry: 0,
            offset: 0,
            "stop-opacity": 1,
            "stop-color": "#000000",
            "font-size": 16,
            "font-family": "Helvetica, Arial, sans-serif",
            "text-anchor": "start"
        },
        trans: function() {
            return {
                x: 0,
                y: 0,
                scaleX: 1,
                scaleY: 1,
                rotation: 0,
                skewX: 0,
                skewY: 0,
                matrix: this.matrix,
                a: 1,
                b: 0,
                c: 0,
                d: 1,
                e: 0,
                f: 0
            };
        }
    }, c.Color = function(t) {
        var n;
        this.r = 0, this.g = 0, this.b = 0, "string" == typeof t ? c.regex.isRgb.test(t) ? (n = c.regex.rgb.exec(t.replace(/\s/g, "")),
            this.r = parseInt(n[1]), this.g = parseInt(n[2]), this.b = parseInt(n[3])) : c.regex.isHex.test(t) && (n = c.regex.hex.exec(e(t)),
            this.r = parseInt(n[1], 16), this.g = parseInt(n[2], 16), this.b = parseInt(n[3], 16)) : "object" == typeof t && (this.r = t.r,
            this.g = t.g, this.b = t.b);
    }, c.extend(c.Color, {
        toString: function() {
            return this.toHex();
        },
        toHex: function() {
            return "#" + n(this.r) + n(this.g) + n(this.b);
        },
        toRgb: function() {
            return "rgb(" + [ this.r, this.g, this.b ].join() + ")";
        },
        brightness: function() {
            return this.r / 255 * .3 + this.g / 255 * .59 + this.b / 255 * .11;
        },
        morph: function(t) {
            return this.destination = new c.Color(t), this;
        },
        at: function(t) {
            return this.destination ? (t = 0 > t ? 0 : t > 1 ? 1 : t, new c.Color({
                r: ~~(this.r + (this.destination.r - this.r) * t),
                g: ~~(this.g + (this.destination.g - this.g) * t),
                b: ~~(this.b + (this.destination.b - this.b) * t)
            })) : this;
        }
    }), c.Color.test = function(t) {
        return t += "", c.regex.isHex.test(t) || c.regex.isRgb.test(t);
    }, c.Color.isRgb = function(t) {
        return t && "number" == typeof t.r && "number" == typeof t.g && "number" == typeof t.b;
    }, c.Color.isColor = function(t) {
        return c.Color.isRgb(t) || c.Color.test(t);
    }, c.Array = function(t, e) {
        t = (t || []).valueOf(), 0 == t.length && e && (t = e.valueOf()), this.value = this.parse(t);
    }, c.extend(c.Array, {
        morph: function(t) {
            if (this.destination = this.parse(t), this.value.length != this.destination.length) {
                for (var e = this.value[this.value.length - 1], n = this.destination[this.destination.length - 1]; this.value.length > this.destination.length; ) this.destination.push(n);
                for (;this.value.length < this.destination.length; ) this.value.push(e);
            }
            return this;
        },
        settle: function() {
            for (var t = 0, e = this.value.length, n = []; e > t; t++) -1 == n.indexOf(this.value[t]) && n.push(this.value[t]);
            return this.value = n;
        },
        at: function(t) {
            if (!this.destination) return this;
            for (var e = 0, n = this.value.length, i = []; n > e; e++) i.push(this.value[e] + (this.destination[e] - this.value[e]) * t);
            return new c.Array(i);
        },
        toString: function() {
            return this.value.join(" ");
        },
        valueOf: function() {
            return this.value;
        },
        parse: function(t) {
            return t = t.valueOf(), Array.isArray(t) ? t : this.split(t);
        },
        split: function(t) {
            return t.replace(/\s+/g, " ").replace(/^\s+|\s+$/g, "").split(" ");
        },
        reverse: function() {
            return this.value.reverse(), this;
        }
    }), c.PointArray = function() {
        this.constructor.apply(this, arguments);
    }, c.PointArray.prototype = new c.Array(), c.extend(c.PointArray, {
        toString: function() {
            for (var t = 0, e = this.value.length, n = []; e > t; t++) n.push(this.value[t].join(","));
            return n.join(" ");
        },
        at: function(t) {
            if (!this.destination) return this;
            for (var e = 0, n = this.value.length, i = []; n > e; e++) i.push([ this.value[e][0] + (this.destination[e][0] - this.value[e][0]) * t, this.value[e][1] + (this.destination[e][1] - this.value[e][1]) * t ]);
            return new c.PointArray(i);
        },
        parse: function(t) {
            if (t = t.valueOf(), Array.isArray(t)) return t;
            t = this.split(t);
            for (var e, n = 0, i = t.length, r = []; i > n; n++) e = t[n].split(","), r.push([ parseFloat(e[0]), parseFloat(e[1]) ]);
            return r;
        },
        move: function(t, e) {
            var n = this.bbox();
            if (t -= n.x, e -= n.y, !isNaN(t) && !isNaN(e)) for (var i = this.value.length - 1; i >= 0; i--) this.value[i] = [ this.value[i][0] + t, this.value[i][1] + e ];
            return this;
        },
        size: function(t, e) {
            var n, i = this.bbox();
            for (n = this.value.length - 1; n >= 0; n--) this.value[n][0] = (this.value[n][0] - i.x) * t / i.width + i.x,
                this.value[n][1] = (this.value[n][1] - i.y) * e / i.height + i.y;
            return this;
        },
        bbox: function() {
            return c.parser.poly.setAttribute("points", this.toString()), c.parser.poly.getBBox();
        }
    }), c.PathArray = function(t, e) {
        this.constructor.call(this, t, e);
    }, c.PathArray.prototype = new c.Array(), c.extend(c.PathArray, {
        toString: function() {
            return o(this.value);
        },
        move: function(t, e) {
            var n = this.bbox();
            if (t -= n.x, e -= n.y, !isNaN(t) && !isNaN(e)) for (var i, r = this.value.length - 1; r >= 0; r--) i = this.value[r][0],
                "M" == i || "L" == i || "T" == i ? (this.value[r][1] += t, this.value[r][2] += e) : "H" == i ? this.value[r][1] += t : "V" == i ? this.value[r][1] += e : "C" == i || "S" == i || "Q" == i ? (this.value[r][1] += t,
                    this.value[r][2] += e, this.value[r][3] += t, this.value[r][4] += e, "C" == i && (this.value[r][5] += t,
                    this.value[r][6] += e)) : "A" == i && (this.value[r][6] += t, this.value[r][7] += e);
            return this;
        },
        size: function(t, e) {
            var n, i, r = this.bbox();
            for (n = this.value.length - 1; n >= 0; n--) i = this.value[n][0], "M" == i || "L" == i || "T" == i ? (this.value[n][1] = (this.value[n][1] - r.x) * t / r.width + r.x,
                this.value[n][2] = (this.value[n][2] - r.y) * e / r.height + r.y) : "H" == i ? this.value[n][1] = (this.value[n][1] - r.x) * t / r.width + r.x : "V" == i ? this.value[n][1] = (this.value[n][1] - r.y) * e / r.height + r.y : "C" == i || "S" == i || "Q" == i ? (this.value[n][1] = (this.value[n][1] - r.x) * t / r.width + r.x,
                this.value[n][2] = (this.value[n][2] - r.y) * e / r.height + r.y, this.value[n][3] = (this.value[n][3] - r.x) * t / r.width + r.x,
                this.value[n][4] = (this.value[n][4] - r.y) * e / r.height + r.y, "C" == i && (this.value[n][5] = (this.value[n][5] - r.x) * t / r.width + r.x,
                this.value[n][6] = (this.value[n][6] - r.y) * e / r.height + r.y)) : "A" == i && (this.value[n][1] = this.value[n][1] * t / r.width,
                this.value[n][2] = this.value[n][2] * e / r.height, this.value[n][6] = (this.value[n][6] - r.x) * t / r.width + r.x,
                this.value[n][7] = (this.value[n][7] - r.y) * e / r.height + r.y);
            return this;
        },
        parse: function(t) {
            if (t instanceof c.PathArray) return t.valueOf();
            var e, n, i, r, s, a, u, l, h, f, d, p = 0, m = 0;
            for (c.parser.path.setAttribute("d", "string" == typeof t ? t : o(t)), d = c.parser.path.pathSegList,
                     e = 0, n = d.numberOfItems; n > e; ++e) f = d.getItem(e), h = f.pathSegTypeAsLetter,
                "M" == h || "L" == h || "H" == h || "V" == h || "C" == h || "S" == h || "Q" == h || "T" == h || "A" == h ? ("x" in f && (p = f.x),
                "y" in f && (m = f.y)) : ("x1" in f && (s = p + f.x1), "x2" in f && (u = p + f.x2),
                "y1" in f && (a = m + f.y1), "y2" in f && (l = m + f.y2), "x" in f && (p += f.x),
                "y" in f && (m += f.y), "m" == h ? d.replaceItem(c.parser.path.createSVGPathSegMovetoAbs(p, m), e) : "l" == h ? d.replaceItem(c.parser.path.createSVGPathSegLinetoAbs(p, m), e) : "h" == h ? d.replaceItem(c.parser.path.createSVGPathSegLinetoHorizontalAbs(p), e) : "v" == h ? d.replaceItem(c.parser.path.createSVGPathSegLinetoVerticalAbs(m), e) : "c" == h ? d.replaceItem(c.parser.path.createSVGPathSegCurvetoCubicAbs(p, m, s, a, u, l), e) : "s" == h ? d.replaceItem(c.parser.path.createSVGPathSegCurvetoCubicSmoothAbs(p, m, u, l), e) : "q" == h ? d.replaceItem(c.parser.path.createSVGPathSegCurvetoQuadraticAbs(p, m, s, a), e) : "t" == h ? d.replaceItem(c.parser.path.createSVGPathSegCurvetoQuadraticSmoothAbs(p, m), e) : "a" == h ? d.replaceItem(c.parser.path.createSVGPathSegArcAbs(p, m, f.r1, f.r2, f.angle, f.largeArcFlag, f.sweepFlag), e) : ("z" == h || "Z" == h) && (p = i,
                    m = r)), ("M" == h || "m" == h) && (i = p, r = m);
            for (t = [], d = c.parser.path.pathSegList, e = 0, n = d.numberOfItems; n > e; ++e) f = d.getItem(e),
                h = f.pathSegTypeAsLetter, p = [ h ], "M" == h || "L" == h || "T" == h ? p.push(f.x, f.y) : "H" == h ? p.push(f.x) : "V" == h ? p.push(f.y) : "C" == h ? p.push(f.x1, f.y1, f.x2, f.y2, f.x, f.y) : "S" == h ? p.push(f.x2, f.y2, f.x, f.y) : "Q" == h ? p.push(f.x1, f.y1, f.x, f.y) : "A" == h && p.push(f.r1, f.r2, f.angle, 0 | f.largeArcFlag, 0 | f.sweepFlag, f.x, f.y),
                t.push(p);
            return t;
        },
        bbox: function() {
            return c.parser.path.setAttribute("d", this.toString()), c.parser.path.getBBox();
        }
    }), c.Number = function(t) {
        if (this.value = 0, this.unit = "", "number" == typeof t) this.value = isNaN(t) ? 0 : isFinite(t) ? t : 0 > t ? -3.4e38 : 3.4e38; else if ("string" == typeof t) {
            var e = t.match(c.regex.unit);
            e && (this.value = parseFloat(e[1]), "%" == e[2] ? this.value /= 100 : "s" == e[2] && (this.value *= 1e3),
                this.unit = e[2]);
        } else t instanceof c.Number && (this.value = t.value, this.unit = t.unit);
    }, c.extend(c.Number, {
        toString: function() {
            return ("%" == this.unit ? ~~(1e8 * this.value) / 1e6 : "s" == this.unit ? this.value / 1e3 : this.value) + this.unit;
        },
        valueOf: function() {
            return this.value;
        },
        plus: function(t) {
            return this.value = this + new c.Number(t), this;
        },
        minus: function(t) {
            return this.plus(-new c.Number(t));
        },
        times: function(t) {
            return this.value = this * new c.Number(t), this;
        },
        divide: function(t) {
            return this.value = this / new c.Number(t), this;
        },
        to: function(t) {
            return "string" == typeof t && (this.unit = t), this;
        },
        morph: function(t) {
            return this.destination = new c.Number(t), this;
        },
        at: function(t) {
            return this.destination ? new c.Number(this.destination).minus(this).times(t).plus(this) : this;
        }
    }), c.ViewBox = function(t) {
        var e, n, i, r, o = 1, s = 1, a = t.bbox(), u = (t.attr("viewBox") || "").match(/-?[\d\.]+/g), l = t, h = t;
        for (i = new c.Number(t.width()), r = new c.Number(t.height()); "%" == i.unit; ) o *= i.value,
            i = new c.Number(l instanceof c.Doc ? l.parent.offsetWidth : l.parent.width()),
            l = l.parent;
        for (;"%" == r.unit; ) s *= r.value, r = new c.Number(h instanceof c.Doc ? h.parent.offsetHeight : h.parent.height()),
            h = h.parent;
        this.x = a.x, this.y = a.y, this.width = i * o, this.height = r * s, this.zoom = 1,
        u && (e = parseFloat(u[0]), n = parseFloat(u[1]), i = parseFloat(u[2]), r = parseFloat(u[3]),
            this.zoom = this.width / this.height > i / r ? this.height / r : this.width / i,
            this.x = e, this.y = n, this.width = i, this.height = r);
    }, c.extend(c.ViewBox, {
        toString: function() {
            return this.x + " " + this.y + " " + this.width + " " + this.height;
        }
    }), c.BBox = function(t) {
        var e;
        if (this.x = 0, this.y = 0, this.width = 0, this.height = 0, t) {
            try {
                e = t.node.getBBox();
            } catch (n) {
                e = {
                    x: t.node.clientLeft,
                    y: t.node.clientTop,
                    width: t.node.clientWidth,
                    height: t.node.clientHeight
                };
            }
            this.x = e.x + t.trans.x, this.y = e.y + t.trans.y, this.width = e.width * t.trans.scaleX,
                this.height = e.height * t.trans.scaleY;
        }
        s(this);
    }, c.extend(c.BBox, {
        merge: function(t) {
            var e = new c.BBox();
            return e.x = Math.min(this.x, t.x), e.y = Math.min(this.y, t.y), e.width = Math.max(this.x + this.width, t.x + t.width) - e.x,
                e.height = Math.max(this.y + this.height, t.y + t.height) - e.y, s(e), e;
        }
    }), c.RBox = function(t) {
        var e, n, i = {};
        if (this.x = 0, this.y = 0, this.width = 0, this.height = 0, t) {
            for (e = t.doc().parent, n = t.doc().viewbox().zoom, i = t.node.getBoundingClientRect(),
                     this.x = i.left, this.y = i.top, this.x -= e.offsetLeft, this.y -= e.offsetTop; e = e.offsetParent; ) this.x -= e.offsetLeft,
                this.y -= e.offsetTop;
            for (e = t; e = e.parent; ) "svg" == e.type && e.viewbox && (n *= e.viewbox().zoom,
                this.x -= e.x() || 0, this.y -= e.y() || 0);
        }
        this.x /= n, this.y /= n, this.width = i.width /= n, this.height = i.height /= n,
            this.x += window.scrollX, this.y += window.scrollY, s(this);
    }, c.extend(c.RBox, {
        merge: function(t) {
            var e = new c.RBox();
            return e.x = Math.min(this.x, t.x), e.y = Math.min(this.y, t.y), e.width = Math.max(this.x + this.width, t.x + t.width) - e.x,
                e.height = Math.max(this.y + this.height, t.y + t.height) - e.y, s(e), e;
        }
    }), c.Element = c.invent({
        create: function(t) {
            this._stroke = c.defaults.attrs.stroke, this.trans = c.defaults.trans(), (this.node = t) && (this.type = t.nodeName,
                this.node.instance = this);
        },
        extend: {
            x: function(t) {
                return null != t && (t = new c.Number(t), t.value /= this.trans.scaleX), this.attr("x", t);
            },
            y: function(t) {
                return null != t && (t = new c.Number(t), t.value /= this.trans.scaleY), this.attr("y", t);
            },
            cx: function(t) {
                return null == t ? this.x() + this.width() / 2 : this.x(t - this.width() / 2);
            },
            cy: function(t) {
                return null == t ? this.y() + this.height() / 2 : this.y(t - this.height() / 2);
            },
            move: function(t, e) {
                return this.x(t).y(e);
            },
            center: function(t, e) {
                return this.cx(t).cy(e);
            },
            width: function(t) {
                return this.attr("width", t);
            },
            height: function(t) {
                return this.attr("height", t);
            },
            size: function(t, e) {
                var n = i(this.bbox(), t, e);
                return this.width(new c.Number(n.width)).height(new c.Number(n.height));
            },
            clone: function() {
                var t, e, n = this.type;
                return t = "rect" == n || "ellipse" == n ? this.parent[n](0, 0) : "line" == n ? this.parent[n](0, 0, 0, 0) : "image" == n ? this.parent[n](this.src) : "text" == n ? this.parent[n](this.content) : "path" == n ? this.parent[n](this.attr("d")) : "polyline" == n || "polygon" == n ? this.parent[n](this.attr("points")) : "g" == n ? this.parent.group() : this.parent[n](),
                    e = this.attr(), delete e.id, t.attr(e), t.trans = this.trans, t.transform({});
            },
            remove: function() {
                return this.parent && this.parent.removeElement(this), this;
            },
            replace: function(t) {
                return this.after(t).remove(), t;
            },
            addTo: function(t) {
                return t.put(this);
            },
            putIn: function(t) {
                return t.add(this);
            },
            doc: function(t) {
                return this._parent(t || c.Doc);
            },
            attr: function(t, e, n) {
                if (null == t) {
                    for (t = {}, e = this.node.attributes, n = e.length - 1; n >= 0; n--) t[e[n].nodeName] = c.regex.isNumber.test(e[n].nodeValue) ? parseFloat(e[n].nodeValue) : e[n].nodeValue;
                    return t;
                }
                if ("object" == typeof t) for (e in t) this.attr(e, t[e]); else if (null === e) this.node.removeAttribute(t); else {
                    if (null == e) return e = this.node.getAttribute(t), null == e ? c.defaults.attrs[t] : c.regex.isNumber.test(e) ? parseFloat(e) : e;
                    if ("style" == t) return this.style(e);
                    "stroke-width" == t ? this.attr("stroke", parseFloat(e) > 0 ? this._stroke : null) : "stroke" == t && (this._stroke = e),
                    ("fill" == t || "stroke" == t) && (c.regex.isImage.test(e) && (e = this.doc().defs().image(e, 0, 0)),
                    e instanceof c.Image && (e = this.doc().defs().pattern(0, 0, function() {
                        this.add(e);
                    }))), "number" == typeof e ? e = new c.Number(e) : c.Color.isColor(e) ? e = new c.Color(e) : Array.isArray(e) && (e = new c.Array(e)),
                        "leading" == t ? this.leading && this.leading(e) : "string" == typeof n ? this.node.setAttributeNS(n, t, e.toString()) : this.node.setAttribute(t, e.toString()),
                    !this.rebuild || "font-size" != t && "x" != t || this.rebuild(t, e);
                }
                return this;
            },
            transform: function(t, e) {
                if (0 == arguments.length) return this.trans;
                if ("string" == typeof t) {
                    if (arguments.length < 2) return this.trans[t];
                    var n = {};
                    return n[t] = e, this.transform(n);
                }
                var n = [];
                t = a(t);
                for (e in t) null != t[e] && (this.trans[e] = t[e]);
                return this.trans.matrix = this.trans.a + " " + this.trans.b + " " + this.trans.c + " " + this.trans.d + " " + this.trans.e + " " + this.trans.f,
                    t = this.trans, t.matrix != c.defaults.matrix && n.push("matrix(" + t.matrix + ")"),
                0 != t.rotation && n.push("rotate(" + t.rotation + " " + (null == t.cx ? this.bbox().cx : t.cx) + " " + (null == t.cy ? this.bbox().cy : t.cy) + ")"),
                (1 != t.scaleX || 1 != t.scaleY) && n.push("scale(" + t.scaleX + " " + t.scaleY + ")"),
                0 != t.skewX && n.push("skewX(" + t.skewX + ")"), 0 != t.skewY && n.push("skewY(" + t.skewY + ")"),
                (0 != t.x || 0 != t.y) && n.push("translate(" + new c.Number(t.x / t.scaleX) + " " + new c.Number(t.y / t.scaleY) + ")"),
                    0 == n.length ? this.node.removeAttribute("transform") : this.node.setAttribute("transform", n.join(" ")),
                    this;
            },
            style: function(e, n) {
                if (0 == arguments.length) return this.node.style.cssText || "";
                if (arguments.length < 2) if ("object" == typeof e) for (n in e) this.style(n, e[n]); else {
                    if (!c.regex.isCss.test(e)) return this.node.style[t(e)];
                    e = e.split(";");
                    for (var i = 0; i < e.length; i++) n = e[i].split(":"), this.style(n[0].replace(/\s+/g, ""), n[1]);
                } else this.node.style[t(e)] = null === n || c.regex.isBlank.test(n) ? "" : n;
                return this;
            },
            id: function(t) {
                return this.attr("id", t);
            },
            bbox: function() {
                return new c.BBox(this);
            },
            rbox: function() {
                return new c.RBox(this);
            },
            inside: function(t, e) {
                var n = this.bbox();
                return t > n.x && e > n.y && t < n.x + n.width && e < n.y + n.height;
            },
            show: function() {
                return this.style("display", "");
            },
            hide: function() {
                return this.style("display", "none");
            },
            visible: function() {
                return "none" != this.style("display");
            },
            toString: function() {
                return this.attr("id");
            },
            classes: function() {
                var t = this.node.getAttribute("class");
                return null === t ? [] : t.trim().split(/\s+/);
            },
            hasClass: function(t) {
                return -1 != this.classes().indexOf(t);
            },
            addClass: function(t) {
                var e;
                return this.hasClass(t) || (e = this.classes(), e.push(t), this.node.setAttribute("class", e.join(" "))),
                    this;
            },
            removeClass: function(t) {
                var e;
                return this.hasClass(t) && (e = this.classes().filter(function(e) {
                    return e != t;
                }), this.node.setAttribute("class", e.join(" "))), this;
            },
            toggleClass: function(t) {
                return this.hasClass(t) ? this.removeClass(t) : this.addClass(t), this;
            },
            reference: function(t) {
                return c.get(this.attr(t));
            },
            _parent: function(t) {
                for (var e = this; null != e && !(e instanceof t); ) e = e.parent;
                return e;
            }
        }
    }), c.Parent = c.invent({
        create: function(t) {
            this.constructor.call(this, t);
        },
        inherit: c.Element,
        extend: {
            children: function() {
                return this._children || (this._children = []);
            },
            add: function(t, e) {
                return this.has(t) || (e = null == e ? this.children().length : e, t.parent && t.parent.children().splice(t.parent.index(t), 1),
                    this.children().splice(e, 0, t), this.node.insertBefore(t.node, this.node.childNodes[e] || null),
                    t.parent = this), this._defs && (this.node.removeChild(this._defs.node), this.node.appendChild(this._defs.node)),
                    this;
            },
            put: function(t, e) {
                return this.add(t, e), t;
            },
            has: function(t) {
                return this.index(t) >= 0;
            },
            index: function(t) {
                return this.children().indexOf(t);
            },
            get: function(t) {
                return this.children()[t];
            },
            first: function() {
                return this.children()[0];
            },
            last: function() {
                return this.children()[this.children().length - 1];
            },
            each: function(t, e) {
                var n, i, r = this.children();
                for (n = 0, i = r.length; i > n; n++) r[n] instanceof c.Element && t.apply(r[n], [ n, r ]),
                e && r[n] instanceof c.Container && r[n].each(t, e);
                return this;
            },
            removeElement: function(t) {
                return this.children().splice(this.index(t), 1), this.node.removeChild(t.node),
                    t.parent = null, this;
            },
            clear: function() {
                for (var t = this.children().length - 1; t >= 0; t--) this.removeElement(this.children()[t]);
                return this._defs && this._defs.clear(), this;
            },
            defs: function() {
                return this.doc().defs();
            }
        }
    }), c.Container = c.invent({
        create: function(t) {
            this.constructor.call(this, t);
        },
        inherit: c.Parent,
        extend: {
            viewbox: function(t) {
                return 0 == arguments.length ? new c.ViewBox(this) : (t = 1 == arguments.length ? [ t.x, t.y, t.width, t.height ] : [].slice.call(arguments),
                    this.attr("viewBox", t));
            }
        }
    }), c.FX = c.invent({
        create: function(t) {
            this.target = t;
        },
        extend: {
            animate: function(t, e, n) {
                var i, o, s, a, u = this.target, l = this;
                return "object" == typeof t && (n = t.delay, e = t.ease, t = t.duration), t = "=" == t ? t : null == t ? 1e3 : new c.Number(t).valueOf(),
                    e = e || "<>", l.to = function(t) {
                    var n;
                    if (t = 0 > t ? 0 : t > 1 ? 1 : t, null == i) {
                        i = [];
                        for (a in l.attrs) i.push(a);
                        if (u.morphArray && (l._plot || i.indexOf("points") > -1)) {
                            var c, h = new u.morphArray(l._plot || l.attrs.points || u.array);
                            l._size && h.size(l._size.width.to, l._size.height.to), c = h.bbox(), l._x ? h.move(l._x.to, c.y) : l._cx && h.move(l._cx.to - c.width / 2, c.y),
                                c = h.bbox(), l._y ? h.move(c.x, l._y.to) : l._cy && h.move(c.x, l._cy.to - c.height / 2),
                                delete l._x, delete l._y, delete l._cx, delete l._cy, delete l._size, l._plot = u.array.morph(h);
                        }
                    }
                    if (null == o) {
                        o = [];
                        for (a in l.trans) o.push(a);
                    }
                    if (null == s) {
                        s = [];
                        for (a in l.styles) s.push(a);
                    }
                    for (t = "<>" == e ? -Math.cos(t * Math.PI) / 2 + .5 : ">" == e ? Math.sin(t * Math.PI / 2) : "<" == e ? -Math.cos(t * Math.PI / 2) + 1 : "-" == e ? t : "function" == typeof e ? e(t) : t,
                             l._plot ? u.plot(l._plot.at(t)) : (l._x ? u.x(l._x.at(t)) : l._cx && u.cx(l._cx.at(t)),
                                 l._y ? u.y(l._y.at(t)) : l._cy && u.cy(l._cy.at(t)), l._size && u.size(l._size.width.at(t), l._size.height.at(t))),
                         l._viewbox && u.viewbox(l._viewbox.x.at(t), l._viewbox.y.at(t), l._viewbox.width.at(t), l._viewbox.height.at(t)),
                         l._leading && u.leading(l._leading.at(t)), n = i.length - 1; n >= 0; n--) u.attr(i[n], r(l.attrs[i[n]], t));
                    for (n = o.length - 1; n >= 0; n--) u.transform(o[n], r(l.trans[o[n]], t));
                    for (n = s.length - 1; n >= 0; n--) u.style(s[n], r(l.styles[s[n]], t));
                    l._during && l._during.call(u, t, function(e, n) {
                        return r({
                            from: e,
                            to: n
                        }, t);
                    });
                }, "number" == typeof t && (this.timeout = setTimeout(function() {
                    var i = new Date().getTime();
                    l.situation = {
                        interval: 1e3 / 60,
                        start: i,
                        play: !0,
                        finish: i + t,
                        duration: t
                    }, l.render = function() {
                        if (l.situation.play === !0) {
                            var i = new Date().getTime(), r = i > l.situation.finish ? 1 : (i - l.situation.start) / t;
                            l.to(r), i > l.situation.finish ? (l._plot && u.plot(new c.PointArray(l._plot.destination).settle()),
                                l._loop === !0 || "number" == typeof l._loop && l._loop > 1 ? ("number" == typeof l._loop && --l._loop,
                                    l.animate(t, e, n)) : l._after ? l._after.apply(u, [ l ]) : l.stop()) : requestAnimFrame(l.render);
                        } else requestAnimFrame(l.render);
                    }, l.render();
                }, new c.Number(n).valueOf())), this;
            },
            bbox: function() {
                return this.target.bbox();
            },
            attr: function(t, e) {
                if ("object" == typeof t) for (var n in t) this.attr(n, t[n]); else {
                    var i = this.target.attr(t);
                    this.attrs[t] = c.Color.isColor(i) ? new c.Color(i).morph(e) : c.regex.unit.test(i) ? new c.Number(i).morph(e) : {
                        from: i,
                        to: e
                    };
                }
                return this;
            },
            transform: function(t, e) {
                if (1 == arguments.length) {
                    t = a(t), delete t.matrix;
                    for (e in t) this.trans[e] = {
                        from: this.target.trans[e],
                        to: t[e]
                    };
                } else {
                    var n = {};
                    n[t] = e, this.transform(n);
                }
                return this;
            },
            style: function(t, e) {
                if ("object" == typeof t) for (var n in t) this.style(n, t[n]); else this.styles[t] = {
                    from: this.target.style(t),
                    to: e
                };
                return this;
            },
            x: function(t) {
                return this._x = new c.Number(this.target.x()).morph(t), this;
            },
            y: function(t) {
                return this._y = new c.Number(this.target.y()).morph(t), this;
            },
            cx: function(t) {
                return this._cx = new c.Number(this.target.cx()).morph(t), this;
            },
            cy: function(t) {
                return this._cy = new c.Number(this.target.cy()).morph(t), this;
            },
            move: function(t, e) {
                return this.x(t).y(e);
            },
            center: function(t, e) {
                return this.cx(t).cy(e);
            },
            size: function(t, e) {
                if (this.target instanceof c.Text) this.attr("font-size", t); else {
                    var n = this.target.bbox();
                    this._size = {
                        width: new c.Number(n.width).morph(t),
                        height: new c.Number(n.height).morph(e)
                    };
                }
                return this;
            },
            plot: function(t) {
                return this._plot = t, this;
            },
            leading: function(t) {
                return this.target._leading && (this._leading = new c.Number(this.target._leading).morph(t)),
                    this;
            },
            viewbox: function(t, e, n, i) {
                if (this.target instanceof c.Container) {
                    var r = this.target.viewbox();
                    this._viewbox = {
                        x: new c.Number(r.x).morph(t),
                        y: new c.Number(r.y).morph(e),
                        width: new c.Number(r.width).morph(n),
                        height: new c.Number(r.height).morph(i)
                    };
                }
                return this;
            },
            update: function(t) {
                return this.target instanceof c.Stop && (null != t.opacity && this.attr("stop-opacity", t.opacity),
                null != t.color && this.attr("stop-color", t.color), null != t.offset && this.attr("offset", new c.Number(t.offset))),
                    this;
            },
            during: function(t) {
                return this._during = t, this;
            },
            after: function(t) {
                return this._after = t, this;
            },
            loop: function(t) {
                return this._loop = t || !0, this;
            },
            stop: function(t) {
                return t === !0 ? (this.animate(0), this._after && this._after.apply(this.target, [ this ])) : (clearTimeout(this.timeout),
                    this.attrs = {}, this.trans = {}, this.styles = {}, this.situation = {}, delete this._x,
                    delete this._y, delete this._cx, delete this._cy, delete this._size, delete this._plot,
                    delete this._loop, delete this._after, delete this._during, delete this._leading,
                    delete this._viewbox), this;
            },
            pause: function() {
                return this.situation.play === !0 && (this.situation.play = !1, this.situation.pause = new Date().getTime()),
                    this;
            },
            play: function() {
                if (this.situation.play === !1) {
                    var t = new Date().getTime() - this.situation.pause;
                    this.situation.finish += t, this.situation.start += t, this.situation.play = !0;
                }
                return this;
            }
        },
        parent: c.Element,
        construct: {
            animate: function(t, e, n) {
                return (this.fx || (this.fx = new c.FX(this))).stop().animate(t, e, n);
            },
            stop: function(t) {
                return this.fx && this.fx.stop(t), this;
            },
            pause: function() {
                return this.fx && this.fx.pause(), this;
            },
            play: function() {
                return this.fx && this.fx.play(), this;
            }
        }
    }), c.extend(c.Element, c.FX, {
        dx: function(t) {
            return this.x((this.target || this).x() + t);
        },
        dy: function(t) {
            return this.y((this.target || this).y() + t);
        },
        dmove: function(t, e) {
            return this.dx(t).dy(e);
        }
    }), [ "click", "dblclick", "mousedown", "mouseup", "mouseover", "mouseout", "mousemove", "mouseenter", "mouseleave", "touchstart", "touchmove", "touchleave", "touchend", "touchcancel" ].forEach(function(t) {
        c.Element.prototype[t] = function(e) {
            var n = this;
            return this.node["on" + t] = "function" == typeof e ? function() {
                return e.apply(n, arguments);
            } : null, this;
        };
    }), c.events = {}, c.listeners = {}, c.registerEvent = function(t) {
        c.events[t] || (c.events[t] = new Event(t));
    }, c.on = function(t, e, n) {
        var i = n.bind(t.instance || t);
        c.listeners[n] = i, t.addEventListener(e, i, !1);
    }, c.off = function(t, e, n) {
        t.removeEventListener(e, c.listeners[n], !1), delete c.listeners[n];
    }, c.extend(c.Element, {
        on: function(t, e) {
            return c.on(this.node, t, e), this;
        },
        off: function(t, e) {
            return c.off(this.node, t, e), this;
        },
        fire: function(t) {
            return this.node.dispatchEvent(c.events[t]), this;
        }
    }), c.Defs = c.invent({
        create: "defs",
        inherit: c.Container
    }), c.G = c.invent({
        create: "g",
        inherit: c.Container,
        extend: {
            x: function(t) {
                return null == t ? this.trans.x : this.transform("x", t);
            },
            y: function(t) {
                return null == t ? this.trans.y : this.transform("y", t);
            },
            cx: function(t) {
                return null == t ? this.bbox().cx : this.x(t - this.bbox().width / 2);
            },
            cy: function(t) {
                return null == t ? this.bbox().cy : this.y(t - this.bbox().height / 2);
            }
        },
        construct: {
            group: function() {
                return this.put(new c.G());
            }
        }
    }), c.extend(c.Element, {
        siblings: function() {
            return this.parent.children();
        },
        position: function() {
            return this.parent.index(this);
        },
        next: function() {
            return this.siblings()[this.position() + 1];
        },
        previous: function() {
            return this.siblings()[this.position() - 1];
        },
        forward: function() {
            var t = this.position();
            return this.parent.removeElement(this).put(this, t + 1);
        },
        backward: function() {
            var t = this.position();
            return t > 0 && this.parent.removeElement(this).add(this, t - 1), this;
        },
        front: function() {
            return this.parent.removeElement(this).put(this);
        },
        back: function() {
            return this.position() > 0 && this.parent.removeElement(this).add(this, 0), this;
        },
        before: function(t) {
            t.remove();
            var e = this.position();
            return this.parent.add(t, e), this;
        },
        after: function(t) {
            t.remove();
            var e = this.position();
            return this.parent.add(t, e + 1), this;
        }
    }), c.Mask = c.invent({
        create: function() {
            this.constructor.call(this, c.create("mask")), this.targets = [];
        },
        inherit: c.Container,
        extend: {
            remove: function() {
                for (var t = this.targets.length - 1; t >= 0; t--) this.targets[t] && this.targets[t].unmask();
                return delete this.targets, this.parent.removeElement(this), this;
            }
        },
        construct: {
            mask: function() {
                return this.defs().put(new c.Mask());
            }
        }
    }), c.extend(c.Element, {
        maskWith: function(t) {
            return this.masker = t instanceof c.Mask ? t : this.parent.mask().add(t), this.masker.targets.push(this),
                this.attr("mask", 'url("#' + this.masker.attr("id") + '")');
        },
        unmask: function() {
            return delete this.masker, this.attr("mask", null);
        }
    }), c.Clip = c.invent({
        create: function() {
            this.constructor.call(this, c.create("clipPath")), this.targets = [];
        },
        inherit: c.Container,
        extend: {
            remove: function() {
                for (var t = this.targets.length - 1; t >= 0; t--) this.targets[t] && this.targets[t].unclip();
                return delete this.targets, this.parent.removeElement(this), this;
            }
        },
        construct: {
            clip: function() {
                return this.defs().put(new c.Clip());
            }
        }
    }), c.extend(c.Element, {
        clipWith: function(t) {
            return this.clipper = t instanceof c.Clip ? t : this.parent.clip().add(t), this.clipper.targets.push(this),
                this.attr("clip-path", 'url("#' + this.clipper.attr("id") + '")');
        },
        unclip: function() {
            return delete this.clipper, this.attr("clip-path", null);
        }
    }), c.Gradient = c.invent({
        create: function(t) {
            this.constructor.call(this, c.create(t + "Gradient")), this.type = t;
        },
        inherit: c.Container,
        extend: {
            from: function(t, e) {
                return this.attr("radial" == this.type ? {
                    fx: new c.Number(t),
                    fy: new c.Number(e)
                } : {
                    x1: new c.Number(t),
                    y1: new c.Number(e)
                });
            },
            to: function(t, e) {
                return this.attr("radial" == this.type ? {
                    cx: new c.Number(t),
                    cy: new c.Number(e)
                } : {
                    x2: new c.Number(t),
                    y2: new c.Number(e)
                });
            },
            radius: function(t) {
                return "radial" == this.type ? this.attr({
                    r: new c.Number(t)
                }) : this;
            },
            at: function(t, e, n) {
                return this.put(new c.Stop()).update(t, e, n);
            },
            update: function(t) {
                return this.clear(), "function" == typeof t && t.call(this, this), this;
            },
            fill: function() {
                return "url(#" + this.id() + ")";
            },
            toString: function() {
                return this.fill();
            }
        },
        construct: {
            gradient: function(t, e) {
                return this.defs().gradient(t, e);
            }
        }
    }), c.extend(c.Defs, {
        gradient: function(t, e) {
            return this.put(new c.Gradient(t)).update(e);
        }
    }), c.Stop = c.invent({
        create: "stop",
        inherit: c.Element,
        extend: {
            update: function(t) {
                return ("number" == typeof t || t instanceof c.Number) && (t = {
                    offset: arguments[0],
                    color: arguments[1],
                    opacity: arguments[2]
                }), null != t.opacity && this.attr("stop-opacity", t.opacity), null != t.color && this.attr("stop-color", t.color),
                null != t.offset && this.attr("offset", new c.Number(t.offset)), this;
            }
        }
    }), c.Pattern = c.invent({
        create: "pattern",
        inherit: c.Container,
        extend: {
            fill: function() {
                return "url(#" + this.id() + ")";
            },
            update: function(t) {
                return this.clear(), "function" == typeof t && t.call(this, this), this;
            },
            toString: function() {
                return this.fill();
            }
        },
        construct: {
            pattern: function(t, e, n) {
                return this.defs().pattern(t, e, n);
            }
        }
    }), c.extend(c.Defs, {
        pattern: function(t, e, n) {
            return this.put(new c.Pattern()).update(n).attr({
                x: 0,
                y: 0,
                width: t,
                height: e,
                patternUnits: "userSpaceOnUse"
            });
        }
    }), c.Doc = c.invent({
        create: function(t) {
            this.parent = "string" == typeof t ? document.getElementById(t) : t, this.constructor.call(this, "svg" == this.parent.nodeName ? this.parent : c.create("svg")),
                this.attr({
                    xmlns: c.ns,
                    version: "1.1",
                    width: "100%",
                    height: "100%"
                }).attr("xmlns:xlink", c.xlink, c.xmlns), this._defs = new c.Defs(), this._defs.parent = this,
                this.node.appendChild(this._defs.node), this.doSpof = !1, this.parent != this.node && this.stage();
        },
        inherit: c.Container,
        extend: {
            stage: function() {
                var t = this;
                return this.parent.appendChild(this.node), t.spof(), c.on(window, "resize", function() {
                    t.spof();
                }), this;
            },
            defs: function() {
                return this._defs;
            },
            spof: function() {
                if (this.doSpof) {
                    var t = this.node.getScreenCTM();
                    t && this.style("left", -t.e % 1 + "px").style("top", -t.f % 1 + "px");
                }
                return this;
            },
            fixSubPixelOffset: function() {
                return this.doSpof = !0, this;
            }
        }
    }), c.Shape = c.invent({
        create: function(t) {
            this.constructor.call(this, t);
        },
        inherit: c.Element
    }), c.Symbol = c.invent({
        create: "symbol",
        inherit: c.Container,
        construct: {
            symbol: function() {
                return this.defs().put(new c.Symbol());
            }
        }
    }), c.Use = c.invent({
        create: "use",
        inherit: c.Shape,
        extend: {
            element: function(t) {
                return this.target = t, this.attr("href", "#" + t, c.xlink);
            }
        },
        construct: {
            use: function(t) {
                return this.put(new c.Use()).element(t);
            }
        }
    }), c.Rect = c.invent({
        create: "rect",
        inherit: c.Shape,
        construct: {
            rect: function(t, e) {
                return this.put(new c.Rect().size(t, e));
            }
        }
    }), c.Ellipse = c.invent({
        create: "ellipse",
        inherit: c.Shape,
        extend: {
            x: function(t) {
                return null == t ? this.cx() - this.attr("rx") : this.cx(t + this.attr("rx"));
            },
            y: function(t) {
                return null == t ? this.cy() - this.attr("ry") : this.cy(t + this.attr("ry"));
            },
            cx: function(t) {
                return null == t ? this.attr("cx") : this.attr("cx", new c.Number(t).divide(this.trans.scaleX));
            },
            cy: function(t) {
                return null == t ? this.attr("cy") : this.attr("cy", new c.Number(t).divide(this.trans.scaleY));
            },
            width: function(t) {
                return null == t ? 2 * this.attr("rx") : this.attr("rx", new c.Number(t).divide(2));
            },
            height: function(t) {
                return null == t ? 2 * this.attr("ry") : this.attr("ry", new c.Number(t).divide(2));
            },
            size: function(t, e) {
                var n = i(this.bbox(), t, e);
                return this.attr({
                    rx: new c.Number(n.width).divide(2),
                    ry: new c.Number(n.height).divide(2)
                });
            }
        },
        construct: {
            circle: function(t) {
                return this.ellipse(t, t);
            },
            ellipse: function(t, e) {
                return this.put(new c.Ellipse()).size(t, e).move(0, 0);
            }
        }
    }), c.Line = c.invent({
        create: "line",
        inherit: c.Shape,
        extend: {
            x: function(t) {
                var e = this.bbox();
                return null == t ? e.x : this.attr({
                    x1: this.attr("x1") - e.x + t,
                    x2: this.attr("x2") - e.x + t
                });
            },
            y: function(t) {
                var e = this.bbox();
                return null == t ? e.y : this.attr({
                    y1: this.attr("y1") - e.y + t,
                    y2: this.attr("y2") - e.y + t
                });
            },
            cx: function(t) {
                var e = this.bbox().width / 2;
                return null == t ? this.x() + e : this.x(t - e);
            },
            cy: function(t) {
                var e = this.bbox().height / 2;
                return null == t ? this.y() + e : this.y(t - e);
            },
            width: function(t) {
                var e = this.bbox();
                return null == t ? e.width : this.attr(this.attr("x1") < this.attr("x2") ? "x2" : "x1", e.x + t);
            },
            height: function(t) {
                var e = this.bbox();
                return null == t ? e.height : this.attr(this.attr("y1") < this.attr("y2") ? "y2" : "y1", e.y + t);
            },
            size: function(t, e) {
                var n = i(this.bbox(), t, e);
                return this.width(n.width).height(n.height);
            },
            plot: function(t, e, n, i) {
                return this.attr({
                    x1: t,
                    y1: e,
                    x2: n,
                    y2: i
                });
            }
        },
        construct: {
            line: function(t, e, n, i) {
                return this.put(new c.Line().plot(t, e, n, i));
            }
        }
    }), c.Polyline = c.invent({
        create: "polyline",
        inherit: c.Shape,
        construct: {
            polyline: function(t) {
                return this.put(new c.Polyline()).plot(t);
            }
        }
    }), c.Polygon = c.invent({
        create: "polygon",
        inherit: c.Shape,
        construct: {
            polygon: function(t) {
                return this.put(new c.Polygon()).plot(t);
            }
        }
    }), c.extend(c.Polyline, c.Polygon, {
        morphArray: c.PointArray,
        plot: function(t) {
            return this.attr("points", this.array = new c.PointArray(t, [ [ 0, 0 ] ]));
        },
        move: function(t, e) {
            return this.attr("points", this.array.move(t, e));
        },
        x: function(t) {
            return null == t ? this.bbox().x : this.move(t, this.bbox().y);
        },
        y: function(t) {
            return null == t ? this.bbox().y : this.move(this.bbox().x, t);
        },
        width: function(t) {
            var e = this.bbox();
            return null == t ? e.width : this.size(t, e.height);
        },
        height: function(t) {
            var e = this.bbox();
            return null == t ? e.height : this.size(e.width, t);
        },
        size: function(t, e) {
            var n = i(this.bbox(), t, e);
            return this.attr("points", this.array.size(n.width, n.height));
        }
    }), c.Path = c.invent({
        create: "path",
        inherit: c.Shape,
        extend: {
            plot: function(t) {
                return this.attr("d", this.array = new c.PathArray(t, [ [ "M", 0, 0 ] ]));
            },
            move: function(t, e) {
                return this.attr("d", this.array.move(t, e));
            },
            x: function(t) {
                return null == t ? this.bbox().x : this.move(t, this.bbox().y);
            },
            y: function(t) {
                return null == t ? this.bbox().y : this.move(this.bbox().x, t);
            },
            size: function(t, e) {
                var n = i(this.bbox(), t, e);
                return this.attr("d", this.array.size(n.width, n.height));
            },
            width: function(t) {
                return null == t ? this.bbox().width : this.size(t, this.bbox().height);
            },
            height: function(t) {
                return null == t ? this.bbox().height : this.size(this.bbox().width, t);
            }
        },
        construct: {
            path: function(t) {
                return this.put(new c.Path()).plot(t);
            }
        }
    }), c.Image = c.invent({
        create: "image",
        inherit: c.Shape,
        extend: {
            load: function(t) {
                if (!t) return this;
                var e = this, n = document.createElement("img");
                return n.onload = function() {
                    var i = e.doc(c.Pattern);
                    0 == e.width() && 0 == e.height() && e.size(n.width, n.height), i && 0 == i.width() && 0 == i.height() && i.size(e.width(), e.height()),
                    "function" == typeof e._loaded && e._loaded.call(e, {
                        width: n.width,
                        height: n.height,
                        ratio: n.width / n.height,
                        url: t
                    });
                }, this.attr("href", n.src = this.src = t, c.xlink);
            },
            loaded: function(t) {
                return this._loaded = t, this;
            }
        },
        construct: {
            image: function(t, e, n) {
                return this.put(new c.Image()).load(t).size(e || 0, n || e || 0);
            }
        }
    }), c.Text = c.invent({
        create: function() {
            this.constructor.call(this, c.create("text")), this._leading = new c.Number(1.3),
                this._rebuild = !0, this._build = !1, this.attr("font-family", c.defaults.attrs["font-family"]);
        },
        inherit: c.Shape,
        extend: {
            x: function(t) {
                return null == t ? this.attr("x") : (this.textPath || this.lines.each(function() {
                    this.newLined && this.x(t);
                }), this.attr("x", t));
            },
            y: function(t) {
                var e = this.attr("y"), n = "number" == typeof e ? e - this.bbox().y : 0;
                return null == t ? "number" == typeof e ? e - n : e : this.attr("y", "number" == typeof t ? t + n : t);
            },
            cx: function(t) {
                return null == t ? this.bbox().cx : this.x(t - this.bbox().width / 2);
            },
            cy: function(t) {
                return null == t ? this.bbox().cy : this.y(t - this.bbox().height / 2);
            },
            text: function(t) {
                if ("undefined" == typeof t) return this.content;
                if (this.clear().build(!0), "function" == typeof t) t.call(this, this); else {
                    t = (this.content = t).split("\n");
                    for (var e = 0, n = t.length; n > e; e++) this.tspan(t[e]).newLine();
                }
                return this.build(!1).rebuild();
            },
            size: function(t) {
                return this.attr("font-size", t).rebuild();
            },
            leading: function(t) {
                return null == t ? this._leading : (this._leading = new c.Number(t), this.rebuild());
            },
            rebuild: function(t) {
                if ("boolean" == typeof t && (this._rebuild = t), this._rebuild) {
                    var e = this;
                    this.lines.each(function() {
                        this.newLined && (this.textPath || this.attr("x", e.attr("x")), this.attr("dy", e._leading * new c.Number(e.attr("font-size"))));
                    }), this.fire("rebuild");
                }
                return this;
            },
            build: function(t) {
                return this._build = !!t, this;
            }
        },
        construct: {
            text: function(t) {
                return this.put(new c.Text()).text(t);
            },
            plain: function(t) {
                return this.put(new c.Text()).plain(t);
            }
        }
    }), c.TSpan = c.invent({
        create: "tspan",
        inherit: c.Shape,
        extend: {
            text: function(t) {
                return "function" == typeof t ? t.call(this, this) : this.plain(t), this;
            },
            dx: function(t) {
                return this.attr("dx", t);
            },
            dy: function(t) {
                return this.attr("dy", t);
            },
            newLine: function() {
                var t = this.doc(c.Text);
                return this.newLined = !0, this.dy(t._leading * t.attr("font-size")).attr("x", t.x());
            }
        }
    }), c.extend(c.Text, c.TSpan, {
        plain: function(t) {
            return this._build === !1 && this.clear(), this.node.appendChild(document.createTextNode(this.content = t)),
                this;
        },
        tspan: function(t) {
            var e = (this.textPath || this).node, n = new c.TSpan();
            return this._build === !1 && this.clear(), e.appendChild(n.node), n.parent = this,
            this instanceof c.Text && this.lines.add(n), n.text(t);
        },
        clear: function() {
            for (var t = (this.textPath || this).node; t.hasChildNodes(); ) t.removeChild(t.lastChild);
            return this instanceof c.Text && (delete this.lines, this.lines = new c.Set(), this.content = ""),
                this;
        },
        length: function() {
            return this.node.getComputedTextLength();
        }
    }), c.registerEvent("rebuild"), c.TextPath = c.invent({
        create: "textPath",
        inherit: c.Element,
        parent: c.Text,
        construct: {
            path: function(t) {
                for (this.textPath = new c.TextPath(); this.node.hasChildNodes(); ) this.textPath.node.appendChild(this.node.firstChild);
                return this.node.appendChild(this.textPath.node), this.track = this.doc().defs().path(t),
                    this.textPath.parent = this, this.textPath.attr("href", "#" + this.track, c.xlink),
                    this;
            },
            plot: function(t) {
                return this.track && this.track.plot(t), this;
            }
        }
    }), c.Nested = c.invent({
        create: function() {
            this.constructor.call(this, c.create("svg")), this.style("overflow", "visible");
        },
        inherit: c.Container,
        construct: {
            nested: function() {
                return this.put(new c.Nested());
            }
        }
    }), c.A = c.invent({
        create: "a",
        inherit: c.Container,
        extend: {
            to: function(t) {
                return this.attr("href", t, c.xlink);
            },
            show: function(t) {
                return this.attr("show", t, c.xlink);
            },
            target: function(t) {
                return this.attr("target", t);
            }
        },
        construct: {
            link: function(t) {
                return this.put(new c.A()).to(t);
            }
        }
    }), c.extend(c.Element, {
        linkTo: function(t) {
            var e = new c.A();
            return "function" == typeof t ? t.call(e, e) : e.to(t), this.parent.put(e).put(this);
        }
    }), c.Marker = c.invent({
        create: "marker",
        inherit: c.Container,
        extend: {
            width: function(t) {
                return this.attr("markerWidth", t);
            },
            height: function(t) {
                return this.attr("markerHeight", t);
            },
            ref: function(t, e) {
                return this.attr("refX", t).attr("refY", e);
            },
            update: function(t) {
                return this.clear(), "function" == typeof t && t.call(this, this), this;
            },
            toString: function() {
                return "url(#" + this.id() + ")";
            }
        },
        construct: {
            marker: function(t, e, n) {
                return this.defs().marker(t, e, n);
            }
        }
    }), c.extend(c.Defs, {
        marker: function(t, e, n) {
            return this.put(new c.Marker()).size(t, e).ref(t / 2, e / 2).viewbox(0, 0, t, e).attr("orient", "auto").update(n);
        }
    }), c.extend(c.Line, c.Polyline, c.Polygon, c.Path, {
        marker: function(t, e, n, i) {
            var r = [ "marker" ];
            return "all" != t && r.push(t), r = r.join("-"), t = arguments[1] instanceof c.Marker ? arguments[1] : this.doc().marker(e, n, i),
                this.attr(r, t);
        }
    });
    var l = {
        stroke: [ "color", "width", "opacity", "linecap", "linejoin", "miterlimit", "dasharray", "dashoffset" ],
        fill: [ "color", "opacity", "rule" ],
        prefix: function(t, e) {
            return "color" == e ? t : t + "-" + e;
        }
    };
    [ "fill", "stroke" ].forEach(function(t) {
        var e, n = {};
        n[t] = function(n) {
            if ("string" == typeof n || c.Color.isRgb(n) || n && "function" == typeof n.fill) this.attr(t, n); else for (e = l[t].length - 1; e >= 0; e--) null != n[l[t][e]] && this.attr(l.prefix(t, l[t][e]), n[l[t][e]]);
            return this;
        }, c.extend(c.Element, c.FX, n);
    }), c.extend(c.Element, c.FX, {
        rotate: function(t, e, n) {
            return this.transform({
                rotation: t || 0,
                cx: e,
                cy: n
            });
        },
        skew: function(t, e) {
            return this.transform({
                skewX: t || 0,
                skewY: e || 0
            });
        },
        scale: function(t, e) {
            return this.transform({
                scaleX: t,
                scaleY: null == e ? t : e
            });
        },
        translate: function(t, e) {
            return this.transform({
                x: t,
                y: e
            });
        },
        matrix: function(t) {
            return this.transform({
                matrix: t
            });
        },
        opacity: function(t) {
            return this.attr("opacity", t);
        }
    }), c.extend(c.Rect, c.Ellipse, c.FX, {
        radius: function(t, e) {
            return this.attr({
                rx: t,
                ry: e || t
            });
        }
    }), c.extend(c.Path, {
        length: function() {
            return this.node.getTotalLength();
        },
        pointAt: function(t) {
            return this.node.getPointAtLength(t);
        }
    }), c.extend(c.Parent, c.Text, c.FX, {
        font: function(t) {
            for (var e in t) "leading" == e ? this.leading(t[e]) : "anchor" == e ? this.attr("text-anchor", t[e]) : "size" == e || "family" == e || "weight" == e || "stretch" == e || "variant" == e || "style" == e ? this.attr("font-" + e, t[e]) : this.attr(e, t[e]);
            return this;
        }
    }), c.Set = c.invent({
        create: function() {
            this.clear();
        },
        extend: {
            add: function() {
                var t, e, n = [].slice.call(arguments);
                for (t = 0, e = n.length; e > t; t++) this.members.push(n[t]);
                return this;
            },
            remove: function(t) {
                var e = this.index(t);
                return e > -1 && this.members.splice(e, 1), this;
            },
            each: function(t) {
                for (var e = 0, n = this.members.length; n > e; e++) t.apply(this.members[e], [ e, this.members ]);
                return this;
            },
            clear: function() {
                return this.members = [], this;
            },
            has: function(t) {
                return this.index(t) >= 0;
            },
            index: function(t) {
                return this.members.indexOf(t);
            },
            get: function(t) {
                return this.members[t];
            },
            first: function() {
                return this.get(0);
            },
            last: function() {
                return this.get(this.members.length - 1);
            },
            valueOf: function() {
                return this.members;
            },
            bbox: function() {
                var t = new c.BBox();
                if (0 == this.members.length) return t;
                var e = this.members[0].rbox();
                return t.x = e.x, t.y = e.y, t.width = e.width, t.height = e.height, this.each(function() {
                    t = t.merge(this.rbox());
                }), t;
            }
        },
        construct: {
            set: function() {
                return new c.Set();
            }
        }
    }), c.SetFX = c.invent({
        create: function(t) {
            this.set = t;
        }
    }), c.Set.inherit = function() {
        var t, e = [];
        for (var t in c.Shape.prototype) "function" == typeof c.Shape.prototype[t] && "function" != typeof c.Set.prototype[t] && e.push(t);
        e.forEach(function(t) {
            c.Set.prototype[t] = function() {
                for (var e = 0, n = this.members.length; n > e; e++) this.members[e] && "function" == typeof this.members[e][t] && this.members[e][t].apply(this.members[e], arguments);
                return "animate" == t ? this.fx || (this.fx = new c.SetFX(this)) : this;
            };
        }), e = [];
        for (var t in c.FX.prototype) "function" == typeof c.FX.prototype[t] && "function" != typeof c.SetFX.prototype[t] && e.push(t);
        e.forEach(function(t) {
            c.SetFX.prototype[t] = function() {
                for (var e = 0, n = this.set.members.length; n > e; e++) this.set.members[e].fx[t].apply(this.set.members[e].fx, arguments);
                return this;
            };
        });
    }, c.extend(c.Element, {
        data: function(t, e, n) {
            if ("object" == typeof t) for (e in t) this.data(e, t[e]); else if (arguments.length < 2) try {
                return JSON.parse(this.attr("data-" + t));
            } catch (i) {
                return this.attr("data-" + t);
            } else this.attr("data-" + t, null === e ? null : n === !0 || "string" == typeof e || "number" == typeof e ? e : JSON.stringify(e));
            return this;
        }
    }), c.extend(c.Element, {
        remember: function(t, e) {
            if ("object" == typeof arguments[0]) for (var e in t) this.remember(e, t[e]); else {
                if (1 == arguments.length) return this.memory()[t];
                this.memory()[t] = e;
            }
            return this;
        },
        forget: function() {
            if (0 == arguments.length) this._memory = {}; else for (var t = arguments.length - 1; t >= 0; t--) delete this.memory()[arguments[t]];
            return this;
        },
        memory: function() {
            return this._memory || (this._memory = {});
        }
    }), "function" == typeof define && define.amd ? define(function() {
        return c;
    }) : "undefined" != typeof exports && (exports.SVG = c), window.requestAnimFrame = function() {
        return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.msRequestAnimationFrame || function(t) {
                window.setTimeout(t, 1e3 / 60);
            };
    }();
}.call(this), function(t, e) {
    "function" == typeof define && define.amd ? define([ "jquery" ], e) : "object" == typeof exports ? module.exports = e(require("jquery")) : e(t.jQuery);
}(this, function(t) {
    function e(t) {
        if (t in h.style) return t;
        for (var e = [ "Moz", "Webkit", "O", "ms" ], n = t.charAt(0).toUpperCase() + t.substr(1), i = 0; i < e.length; ++i) {
            var r = e[i] + n;
            if (r in h.style) return r;
        }
    }
    function n() {
        return h.style[f.transform] = "", h.style[f.transform] = "rotateY(90deg)", "" !== h.style[f.transform];
    }
    function i(t) {
        return "string" == typeof t && this.parse(t), this;
    }
    function r(t, e, n) {
        e === !0 ? t.queue(n) : e ? t.queue(e, n) : t.each(function() {
            n.call(this);
        });
    }
    function o(e) {
        var n = [];
        return t.each(e, function(e) {
            e = t.camelCase(e), e = t.transit.propertyMap[e] || t.cssProps[e] || e, e = u(e),
            f[e] && (e = u(f[e])), -1 === t.inArray(e, n) && n.push(e);
        }), n;
    }
    function s(e, n, i, r) {
        var s = o(e);
        t.cssEase[i] && (i = t.cssEase[i]);
        var a = "" + l(n) + " " + i;
        parseInt(r, 10) > 0 && (a += " " + l(r));
        var u = [];
        return t.each(s, function(t, e) {
            u.push(e + " " + a);
        }), u.join(", ");
    }
    function a(e, n) {
        n || (t.cssNumber[e] = !0), t.transit.propertyMap[e] = f.transform, t.cssHooks[e] = {
            get: function(n) {
                var i = t(n).css("transit:transform");
                return i.get(e);
            },
            set: function(n, i) {
                var r = t(n).css("transit:transform");
                r.setFromString(e, i), t(n).css({
                    "transit:transform": r
                });
            }
        };
    }
    function u(t) {
        return t.replace(/([A-Z])/g, function(t) {
            return "-" + t.toLowerCase();
        });
    }
    function c(t, e) {
        return "string" != typeof t || t.match(/^[\-0-9\.]+$/) ? "" + t + e : t;
    }
    function l(e) {
        var n = e;
        return "string" != typeof n || n.match(/^[\-0-9\.]+/) || (n = t.fx.speeds[n] || t.fx.speeds._default),
            c(n, "ms");
    }
    t.transit = {
        version: "0.9.11",
        propertyMap: {
            marginLeft: "margin",
            marginRight: "margin",
            marginBottom: "margin",
            marginTop: "margin",
            paddingLeft: "padding",
            paddingRight: "padding",
            paddingBottom: "padding",
            paddingTop: "padding"
        },
        enabled: !0,
        useTransitionEnd: !1
    };
    var h = document.createElement("div"), f = {}, d = navigator.userAgent.toLowerCase().indexOf("chrome") > -1;
    f.transition = e("transition"), f.transitionDelay = e("transitionDelay"), f.transform = e("transform"),
        f.transformOrigin = e("transformOrigin"), f.filter = e("Filter"), f.transform3d = n();
    var p = {
        transition: "transitionend",
        MozTransition: "transitionend",
        OTransition: "oTransitionEnd",
        WebkitTransition: "webkitTransitionEnd",
        msTransition: "MSTransitionEnd"
    }, m = f.transitionEnd = p[f.transition] || null;
    for (var g in f) f.hasOwnProperty(g) && "undefined" == typeof t.support[g] && (t.support[g] = f[g]);
    return h = null, t.cssEase = {
        _default: "ease",
        "in": "ease-in",
        out: "ease-out",
        "in-out": "ease-in-out",
        snap: "cubic-bezier(0,1,.5,1)",
        easeInCubic: "cubic-bezier(.550,.055,.675,.190)",
        easeOutCubic: "cubic-bezier(.215,.61,.355,1)",
        easeInOutCubic: "cubic-bezier(.645,.045,.355,1)",
        easeInCirc: "cubic-bezier(.6,.04,.98,.335)",
        easeOutCirc: "cubic-bezier(.075,.82,.165,1)",
        easeInOutCirc: "cubic-bezier(.785,.135,.15,.86)",
        easeInExpo: "cubic-bezier(.95,.05,.795,.035)",
        easeOutExpo: "cubic-bezier(.19,1,.22,1)",
        easeInOutExpo: "cubic-bezier(1,0,0,1)",
        easeInQuad: "cubic-bezier(.55,.085,.68,.53)",
        easeOutQuad: "cubic-bezier(.25,.46,.45,.94)",
        easeInOutQuad: "cubic-bezier(.455,.03,.515,.955)",
        easeInQuart: "cubic-bezier(.895,.03,.685,.22)",
        easeOutQuart: "cubic-bezier(.165,.84,.44,1)",
        easeInOutQuart: "cubic-bezier(.77,0,.175,1)",
        easeInQuint: "cubic-bezier(.755,.05,.855,.06)",
        easeOutQuint: "cubic-bezier(.23,1,.32,1)",
        easeInOutQuint: "cubic-bezier(.86,0,.07,1)",
        easeInSine: "cubic-bezier(.47,0,.745,.715)",
        easeOutSine: "cubic-bezier(.39,.575,.565,1)",
        easeInOutSine: "cubic-bezier(.445,.05,.55,.95)",
        easeInBack: "cubic-bezier(.6,-.28,.735,.045)",
        easeOutBack: "cubic-bezier(.175, .885,.32,1.275)",
        easeInOutBack: "cubic-bezier(.68,-.55,.265,1.55)"
    }, t.cssHooks["transit:transform"] = {
        get: function(e) {
            return t(e).data("transform") || new i();
        },
        set: function(e, n) {
            var r = n;
            r instanceof i || (r = new i(r)), "WebkitTransform" !== f.transform || d ? e.style[f.transform] = r.toString() : e.style[f.transform] = r.toString(!0),
                t(e).data("transform", r);
        }
    }, t.cssHooks.transform = {
        set: t.cssHooks["transit:transform"].set
    }, t.cssHooks.filter = {
        get: function(t) {
            return t.style[f.filter];
        },
        set: function(t, e) {
            t.style[f.filter] = e;
        }
    }, t.fn.jquery < "1.8" && (t.cssHooks.transformOrigin = {
        get: function(t) {
            return t.style[f.transformOrigin];
        },
        set: function(t, e) {
            t.style[f.transformOrigin] = e;
        }
    }, t.cssHooks.transition = {
        get: function(t) {
            return t.style[f.transition];
        },
        set: function(t, e) {
            t.style[f.transition] = e;
        }
    }), a("scale"), a("scaleX"), a("scaleY"), a("translate"), a("rotate"), a("rotateX"),
        a("rotateY"), a("rotate3d"), a("perspective"), a("skewX"), a("skewY"), a("x", !0),
        a("y", !0), i.prototype = {
        setFromString: function(t, e) {
            var n = "string" == typeof e ? e.split(",") : e.constructor === Array ? e : [ e ];
            n.unshift(t), i.prototype.set.apply(this, n);
        },
        set: function(t) {
            var e = Array.prototype.slice.apply(arguments, [ 1 ]);
            this.setter[t] ? this.setter[t].apply(this, e) : this[t] = e.join(",");
        },
        get: function(t) {
            return this.getter[t] ? this.getter[t].apply(this) : this[t] || 0;
        },
        setter: {
            rotate: function(t) {
                this.rotate = c(t, "deg");
            },
            rotateX: function(t) {
                this.rotateX = c(t, "deg");
            },
            rotateY: function(t) {
                this.rotateY = c(t, "deg");
            },
            scale: function(t, e) {
                void 0 === e && (e = t), this.scale = t + "," + e;
            },
            skewX: function(t) {
                this.skewX = c(t, "deg");
            },
            skewY: function(t) {
                this.skewY = c(t, "deg");
            },
            perspective: function(t) {
                this.perspective = c(t, "px");
            },
            x: function(t) {
                this.set("translate", t, null);
            },
            y: function(t) {
                this.set("translate", null, t);
            },
            translate: function(t, e) {
                void 0 === this._translateX && (this._translateX = 0), void 0 === this._translateY && (this._translateY = 0),
                null !== t && void 0 !== t && (this._translateX = c(t, "px")), null !== e && void 0 !== e && (this._translateY = c(e, "px")),
                    this.translate = this._translateX + "," + this._translateY;
            }
        },
        getter: {
            x: function() {
                return this._translateX || 0;
            },
            y: function() {
                return this._translateY || 0;
            },
            scale: function() {
                var t = (this.scale || "1,1").split(",");
                return t[0] && (t[0] = parseFloat(t[0])), t[1] && (t[1] = parseFloat(t[1])), t[0] === t[1] ? t[0] : t;
            },
            rotate3d: function() {
                for (var t = (this.rotate3d || "0,0,0,0deg").split(","), e = 0; 3 >= e; ++e) t[e] && (t[e] = parseFloat(t[e]));
                return t[3] && (t[3] = c(t[3], "deg")), t;
            }
        },
        parse: function(t) {
            var e = this;
            t.replace(/([a-zA-Z0-9]+)\((.*?)\)/g, function(t, n, i) {
                e.setFromString(n, i);
            });
        },
        toString: function(t) {
            var e = [];
            for (var n in this) if (this.hasOwnProperty(n)) {
                if (!f.transform3d && ("rotateX" === n || "rotateY" === n || "perspective" === n || "transformOrigin" === n)) continue;
                "_" !== n[0] && e.push(t && "scale" === n ? n + "3d(" + this[n] + ",1)" : t && "translate" === n ? n + "3d(" + this[n] + ",0)" : n + "(" + this[n] + ")");
            }
            return e.join(" ");
        }
    }, t.fn.transition = t.fn.transit = function(e, n, i, o) {
        var a = this, u = 0, c = !0, h = jQuery.extend(!0, {}, e);
        "function" == typeof n && (o = n, n = void 0), "object" == typeof n && (i = n.easing,
            u = n.delay || 0, c = "undefined" == typeof n.queue ? !0 : n.queue, o = n.complete,
            n = n.duration), "function" == typeof i && (o = i, i = void 0), "undefined" != typeof h.easing && (i = h.easing,
            delete h.easing), "undefined" != typeof h.duration && (n = h.duration, delete h.duration),
        "undefined" != typeof h.complete && (o = h.complete, delete h.complete), "undefined" != typeof h.queue && (c = h.queue,
            delete h.queue), "undefined" != typeof h.delay && (u = h.delay, delete h.delay),
        "undefined" == typeof n && (n = t.fx.speeds._default), "undefined" == typeof i && (i = t.cssEase._default),
            n = l(n);
        var d = s(h, n, i, u), p = t.transit.enabled && f.transition, g = p ? parseInt(n, 10) + parseInt(u, 10) : 0;
        if (0 === g) {
            var v = function(t) {
                a.css(h), o && o.apply(a), t && t();
            };
            return r(a, c, v), a;
        }
        var y = {}, b = function(n) {
            var i = !1, r = function() {
                i && a.unbind(m, r), g > 0 && a.each(function() {
                    this.style[f.transition] = y[this] || null;
                }), "function" == typeof o && o.apply(a), "function" == typeof n && n();
            };
            g > 0 && m && t.transit.useTransitionEnd ? (i = !0, a.bind(m, r)) : window.setTimeout(r, g),
                a.each(function() {
                    g > 0 && (this.style[f.transition] = d), t(this).css(e);
                });
        }, x = function(t) {
            this.offsetWidth, b(t);
        };
        return r(a, c, x), this;
    }, t.transit.getTransitionValue = s, t;
}), function(t, e, n) {
    function i(t) {
        var e = {}, i = /^jQuery\d+$/;
        return n.each(t.attributes, function(t, n) {
            n.specified && !i.test(n.name) && (e[n.name] = n.value);
        }), e;
    }
    function r(t, i) {
        var r = this, o = n(r);
        if (r.value == o.attr("placeholder") && o.hasClass("placeholder")) if (o.data("placeholder-password")) {
            if (o = o.hide().next().show().attr("id", o.removeAttr("id").data("placeholder-id")),
                t === !0) return o[0].value = i;
            o.focus();
        } else r.value = "", o.removeClass("placeholder"), r == e.activeElement && r.select();
    }
    function o() {
        var t, e = this, o = n(e), s = this.id;
        if ("" == e.value) {
            if ("password" == e.type) {
                if (!o.data("placeholder-textinput")) {
                    try {
                        t = o.clone().attr({
                            type: "text"
                        });
                    } catch (a) {
                        t = n("<input>").attr(n.extend(i(this), {
                            type: "text"
                        }));
                    }
                    t.removeAttr("name").data({
                        "placeholder-password": !0,
                        "placeholder-id": s
                    }).bind("focus.placeholder", r), o.data({
                        "placeholder-textinput": t,
                        "placeholder-id": s
                    }).before(t);
                }
                o = o.removeAttr("id").hide().prev().attr("id", s).show();
            }
            o.addClass("placeholder"), o[0].value = o.attr("placeholder");
        } else o.removeClass("placeholder");
    }
    var s, a, u = "placeholder" in e.createElement("input"), c = "placeholder" in e.createElement("textarea"), l = n.fn, h = n.valHooks;
    u && c ? (a = l.placeholder = function() {
        return this;
    }, a.input = a.textarea = !0) : (a = l.placeholder = function() {
        var t = this;
        return t.filter((u ? "textarea" : ":input") + "[placeholder]").not(".placeholder").bind({
            "focus.placeholder": r,
            "blur.placeholder": o
        }).data("placeholder-enabled", !0).trigger("blur.placeholder"), t;
    }, a.input = u, a.textarea = c, s = {
        get: function(t) {
            var e = n(t);
            return e.data("placeholder-enabled") && e.hasClass("placeholder") ? "" : t.value;
        },
        set: function(t, i) {
            var s = n(t);
            return s.data("placeholder-enabled") ? ("" == i ? (t.value = i, t != e.activeElement && o.call(t)) : s.hasClass("placeholder") ? r.call(t, !0, i) || (t.value = i) : t.value = i,
                s) : t.value = i;
        }
    }, u || (h.input = s), c || (h.textarea = s), n(function() {
        n(e).delegate("form", "submit.placeholder", function() {
            var t = n(".placeholder", this).each(r);
            setTimeout(function() {
                t.each(o);
            }, 10);
        });
    }), n(t).bind("beforeunload.placeholder", function() {
        n(".placeholder").each(function() {
            this.value = "";
        });
    }));
}(this, document, jQuery);

var hljs = new function() {
    function t(t) {
        return t.replace(/&/gm, "&amp;").replace(/</gm, "&lt;").replace(/>/gm, "&gt;");
    }
    function e(t) {
        return t.nodeName.toLowerCase();
    }
    function n(t, e) {
        var n = t && t.exec(e);
        return n && 0 == n.index;
    }
    function i(t) {
        var e = (t.className + " " + (t.parentNode ? t.parentNode.className : "")).split(/\s+/);
        return e = e.map(function(t) {
            return t.replace(/^lang(uage)?-/, "");
        }), e.filter(function(t) {
            return v(t) || /no(-?)highlight/.test(t);
        })[0];
    }
    function r(t, e) {
        var n = {};
        for (var i in t) n[i] = t[i];
        if (e) for (var i in e) n[i] = e[i];
        return n;
    }
    function o(t) {
        var n = [];
        return function i(t, r) {
            for (var o = t.firstChild; o; o = o.nextSibling) 3 == o.nodeType ? r += o.nodeValue.length : 1 == o.nodeType && (n.push({
                event: "start",
                offset: r,
                node: o
            }), r = i(o, r), e(o).match(/br|hr|img|input/) || n.push({
                event: "stop",
                offset: r,
                node: o
            }));
            return r;
        }(t, 0), n;
    }
    function s(n, i, r) {
        function o() {
            return n.length && i.length ? n[0].offset != i[0].offset ? n[0].offset < i[0].offset ? n : i : "start" == i[0].event ? n : i : n.length ? n : i;
        }
        function s(n) {
            function i(e) {
                return " " + e.nodeName + '="' + t(e.value) + '"';
            }
            l += "<" + e(n) + Array.prototype.map.call(n.attributes, i).join("") + ">";
        }
        function a(t) {
            l += "</" + e(t) + ">";
        }
        function u(t) {
            ("start" == t.event ? s : a)(t.node);
        }
        for (var c = 0, l = "", h = []; n.length || i.length; ) {
            var f = o();
            if (l += t(r.substr(c, f[0].offset - c)), c = f[0].offset, f == n) {
                h.reverse().forEach(a);
                do u(f.splice(0, 1)[0]), f = o(); while (f == n && f.length && f[0].offset == c);
                h.reverse().forEach(s);
            } else "start" == f[0].event ? h.push(f[0].node) : h.pop(), u(f.splice(0, 1)[0]);
        }
        return l + t(r.substr(c));
    }
    function a(t) {
        function e(t) {
            return t && t.source || t;
        }
        function n(n, i) {
            return RegExp(e(n), "m" + (t.cI ? "i" : "") + (i ? "g" : ""));
        }
        function i(o, s) {
            if (!o.compiled) {
                if (o.compiled = !0, o.k = o.k || o.bK, o.k) {
                    var a = {}, u = function(e, n) {
                        t.cI && (n = n.toLowerCase()), n.split(" ").forEach(function(t) {
                            var n = t.split("|");
                            a[n[0]] = [ e, n[1] ? Number(n[1]) : 1 ];
                        });
                    };
                    "string" == typeof o.k ? u("keyword", o.k) : Object.keys(o.k).forEach(function(t) {
                        u(t, o.k[t]);
                    }), o.k = a;
                }
                o.lR = n(o.l || /\b[A-Za-z0-9_]+\b/, !0), s && (o.bK && (o.b = "\\b(" + o.bK.split(" ").join("|") + ")\\b"),
                o.b || (o.b = /\B|\b/), o.bR = n(o.b), o.e || o.eW || (o.e = /\B|\b/), o.e && (o.eR = n(o.e)),
                    o.tE = e(o.e) || "", o.eW && s.tE && (o.tE += (o.e ? "|" : "") + s.tE)), o.i && (o.iR = n(o.i)),
                void 0 === o.r && (o.r = 1), o.c || (o.c = []);
                var c = [];
                o.c.forEach(function(t) {
                    t.v ? t.v.forEach(function(e) {
                        c.push(r(t, e));
                    }) : c.push("self" == t ? o : t);
                }), o.c = c, o.c.forEach(function(t) {
                    i(t, o);
                }), o.starts && i(o.starts, s);
                var l = o.c.map(function(t) {
                    return t.bK ? "\\.?(" + t.b + ")\\.?" : t.b;
                }).concat([ o.tE, o.i ]).map(e).filter(Boolean);
                o.t = l.length ? n(l.join("|"), !0) : {
                    exec: function(t) {
                        return null;
                    }
                };
            }
        }
        i(t);
    }
    function u(e, i, r, o) {
        function s(t, e) {
            for (var i = 0; i < e.c.length; i++) if (n(e.c[i].bR, t)) return e.c[i];
        }
        function l(t, e) {
            return n(t.eR, e) ? t : t.eW ? l(t.parent, e) : void 0;
        }
        function h(t, e) {
            return !r && n(e.iR, t);
        }
        function f(t, e) {
            var n = _.cI ? e[0].toLowerCase() : e[0];
            return t.k.hasOwnProperty(n) && t.k[n];
        }
        function d(t, e, n, i) {
            var r = i ? "" : y.classPrefix, o = '<span class="' + r, s = n ? "" : "</span>";
            return o += t + '">', o + e + s;
        }
        function p() {
            if (!C.k) return t(A);
            var e = "", n = 0;
            C.lR.lastIndex = 0;
            for (var i = C.lR.exec(A); i; ) {
                e += t(A.substr(n, i.index - n));
                var r = f(C, i);
                r ? (N += r[1], e += d(r[0], t(i[0]))) : e += t(i[0]), n = C.lR.lastIndex, i = C.lR.exec(A);
            }
            return e + t(A.substr(n));
        }
        function m() {
            if (C.sL && !b[C.sL]) return t(A);
            var e = C.sL ? u(C.sL, A, !0, k) : c(A);
            return C.r > 0 && (N += e.r), "continuous" == C.subLanguageMode && (k = e.top),
                d(e.language, e.value, !1, !0);
        }
        function g() {
            return void 0 !== C.sL ? m() : p();
        }
        function x(e, n) {
            var i = e.cN ? d(e.cN, "", !0) : "";
            e.rB ? (E += i, A = "") : e.eB ? (E += t(n) + i, A = "") : (E += i, A = n), C = Object.create(e, {
                parent: {
                    value: C
                }
            });
        }
        function w(e, n) {
            if (A += e, void 0 === n) return E += g(), 0;
            var i = s(n, C);
            if (i) return E += g(), x(i, n), i.rB ? 0 : n.length;
            var r = l(C, n);
            if (r) {
                var o = C;
                o.rE || o.eE || (A += n), E += g();
                do C.cN && (E += "</span>"), N += C.r, C = C.parent; while (C != r.parent);
                return o.eE && (E += t(n)), A = "", r.starts && x(r.starts, ""), o.rE ? 0 : n.length;
            }
            if (h(n, C)) throw new Error('Illegal lexeme "' + n + '" for mode "' + (C.cN || "<unnamed>") + '"');
            return A += n, n.length || 1;
        }
        var _ = v(e);
        if (!_) throw new Error('Unknown language: "' + e + '"');
        a(_);
        for (var k, C = o || _, E = "", T = C; T != _; T = T.parent) T.cN && (E = d(T.cN, "", !0) + E);
        var A = "", N = 0;
        try {
            for (var S, F, $ = 0; ;) {
                if (C.t.lastIndex = $, S = C.t.exec(i), !S) break;
                F = w(i.substr($, S.index - $), S[0]), $ = S.index + F;
            }
            w(i.substr($));
            for (var T = C; T.parent; T = T.parent) T.cN && (E += "</span>");
            return {
                r: N,
                value: E,
                language: e,
                top: C
            };
        } catch (j) {
            if (-1 != j.message.indexOf("Illegal")) return {
                r: 0,
                value: t(i)
            };
            throw j;
        }
    }
    function c(e, n) {
        n = n || y.languages || Object.keys(b);
        var i = {
            r: 0,
            value: t(e)
        }, r = i;
        return n.forEach(function(t) {
            if (v(t)) {
                var n = u(t, e, !1);
                n.language = t, n.r > r.r && (r = n), n.r > i.r && (r = i, i = n);
            }
        }), r.language && (i.second_best = r), i;
    }
    function l(t) {
        return y.tabReplace && (t = t.replace(/^((<[^>]+>|\t)+)/gm, function(t, e, n, i) {
            return e.replace(/\t/g, y.tabReplace);
        })), y.useBR && (t = t.replace(/\n/g, "<br>")), t;
    }
    function h(t) {
        var e = i(t);
        if (!/no(-?)highlight/.test(e)) {
            var n;
            y.useBR ? (n = document.createElementNS("http://www.w3.org/1999/xhtml", "div"),
                n.innerHTML = t.innerHTML.replace(/\n/g, "").replace(/<br[ \/]*>/g, "\n")) : n = t;
            var r = n.textContent, a = e ? u(e, r, !0) : c(r), h = o(n);
            if (h.length) {
                var f = document.createElementNS("http://www.w3.org/1999/xhtml", "div");
                f.innerHTML = a.value, a.value = s(h, o(f), r);
            }
            a.value = l(a.value), t.innerHTML = a.value, t.className += " hljs " + (!e && a.language || ""),
                t.result = {
                    language: a.language,
                    re: a.r
                }, a.second_best && (t.second_best = {
                language: a.second_best.language,
                re: a.second_best.r
            });
        }
    }
    function f(t) {
        y = r(y, t);
    }
    function d() {
        if (!d.called) {
            d.called = !0;
            var t = document.querySelectorAll("pre code");
            Array.prototype.forEach.call(t, h);
        }
    }
    function p() {
        addEventListener("DOMContentLoaded", d, !1), addEventListener("load", d, !1);
    }
    function m(t, e) {
        var n = b[t] = e(this);
        n.aliases && n.aliases.forEach(function(e) {
            x[e] = t;
        });
    }
    function g() {
        return Object.keys(b);
    }
    function v(t) {
        return b[t] || b[x[t]];
    }
    var y = {
        classPrefix: "hljs-",
        tabReplace: null,
        useBR: !1,
        languages: void 0
    }, b = {}, x = {};
    this.highlight = u, this.highlightAuto = c, this.fixMarkup = l, this.highlightBlock = h,
        this.configure = f, this.initHighlighting = d, this.initHighlightingOnLoad = p,
        this.registerLanguage = m, this.listLanguages = g, this.getLanguage = v, this.inherit = r,
        this.IR = "[a-zA-Z][a-zA-Z0-9_]*", this.UIR = "[a-zA-Z_][a-zA-Z0-9_]*", this.NR = "\\b\\d+(\\.\\d+)?",
        this.CNR = "(\\b0[xX][a-fA-F0-9]+|(\\b\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?)",
        this.BNR = "\\b(0b[01]+)", this.RSR = "!|!=|!==|%|%=|&|&&|&=|\\*|\\*=|\\+|\\+=|,|-|-=|/=|/|:|;|<<|<<=|<=|<|===|==|=|>>>=|>>=|>=|>>>|>>|>|\\?|\\[|\\{|\\(|\\^|\\^=|\\||\\|=|\\|\\||~",
        this.BE = {
            b: "\\\\[\\s\\S]",
            r: 0
        }, this.ASM = {
        cN: "string",
        b: "'",
        e: "'",
        i: "\\n",
        c: [ this.BE ]
    }, this.QSM = {
        cN: "string",
        b: '"',
        e: '"',
        i: "\\n",
        c: [ this.BE ]
    }, this.PWM = {
        b: /\b(a|an|the|are|I|I'm|isn't|don't|doesn't|won't|but|just|should|pretty|simply|enough|gonna|going|wtf|so|such)\b/
    }, this.CLCM = {
        cN: "comment",
        b: "//",
        e: "$",
        c: [ this.PWM ]
    }, this.CBCM = {
        cN: "comment",
        b: "/\\*",
        e: "\\*/",
        c: [ this.PWM ]
    }, this.HCM = {
        cN: "comment",
        b: "#",
        e: "$",
        c: [ this.PWM ]
    }, this.NM = {
        cN: "number",
        b: this.NR,
        r: 0
    }, this.CNM = {
        cN: "number",
        b: this.CNR,
        r: 0
    }, this.BNM = {
        cN: "number",
        b: this.BNR,
        r: 0
    }, this.CSSNM = {
        cN: "number",
        b: this.NR + "(%|em|ex|ch|rem|vw|vh|vmin|vmax|cm|mm|in|pt|pc|px|deg|grad|rad|turn|s|ms|Hz|kHz|dpi|dpcm|dppx)?",
        r: 0
    }, this.RM = {
        cN: "regexp",
        b: /\//,
        e: /\/[gim]*/,
        i: /\n/,
        c: [ this.BE, {
            b: /\[/,
            e: /\]/,
            r: 0,
            c: [ this.BE ]
        } ]
    }, this.TM = {
        cN: "title",
        b: this.IR,
        r: 0
    }, this.UTM = {
        cN: "title",
        b: this.UIR,
        r: 0
    };
}();

hljs.registerLanguage("bash", function(t) {
    var e = {
        cN: "variable",
        v: [ {
            b: /\$[\w\d#@][\w\d_]*/
        }, {
            b: /\$\{(.*?)\}/
        } ]
    }, n = {
        cN: "string",
        b: /"/,
        e: /"/,
        c: [ t.BE, e, {
            cN: "variable",
            b: /\$\(/,
            e: /\)/,
            c: [ t.BE ]
        } ]
    }, i = {
        cN: "string",
        b: /'/,
        e: /'/
    };
    return {
        aliases: [ "sh", "zsh" ],
        l: /-?[a-z\.]+/,
        k: {
            keyword: "if then else elif fi for break continue while in do done exit return set declare case esac export exec",
            literal: "true false",
            built_in: "printf echo read cd pwd pushd popd dirs let eval unset typeset readonly getopts source shopt caller type hash bind help sudo",
            operator: "-ne -eq -lt -gt -f -d -e -s -l -a"
        },
        c: [ {
            cN: "shebang",
            b: /^#![^\n]+sh\s*$/,
            r: 10
        }, {
            cN: "function",
            b: /\w[\w\d_]*\s*\(\s*\)\s*\{/,
            rB: !0,
            c: [ t.inherit(t.TM, {
                b: /\w[\w\d_]*/
            }) ],
            r: 0
        }, t.HCM, t.NM, n, i, e ]
    };
}), hljs.registerLanguage("ruby", function(t) {
    var e = "[a-zA-Z_]\\w*[!?=]?|[-+~]\\@|<<|>>|=~|===?|<=>|[<>]=?|\\*\\*|[-/+%^&*~`|]|\\[\\]=?", n = "and false then defined module in return redo if BEGIN retry end for true self when next until do begin unless END rescue nil else break undef not super class case require yield alias while ensure elsif or include attr_reader attr_writer attr_accessor", i = {
        cN: "yardoctag",
        b: "@[A-Za-z]+"
    }, r = {
        cN: "value",
        b: "#<",
        e: ">"
    }, o = {
        cN: "comment",
        v: [ {
            b: "#",
            e: "$",
            c: [ i ]
        }, {
            b: "^\\=begin",
            e: "^\\=end",
            c: [ i ],
            r: 10
        }, {
            b: "^__END__",
            e: "\\n$"
        } ]
    }, s = {
        cN: "subst",
        b: "#\\{",
        e: "}",
        k: n
    }, a = {
        cN: "string",
        c: [ t.BE, s ],
        v: [ {
            b: /'/,
            e: /'/
        }, {
            b: /"/,
            e: /"/
        }, {
            b: "%[qw]?\\(",
            e: "\\)"
        }, {
            b: "%[qw]?\\[",
            e: "\\]"
        }, {
            b: "%[qw]?{",
            e: "}"
        }, {
            b: "%[qw]?<",
            e: ">"
        }, {
            b: "%[qw]?/",
            e: "/"
        }, {
            b: "%[qw]?%",
            e: "%"
        }, {
            b: "%[qw]?-",
            e: "-"
        }, {
            b: "%[qw]?\\|",
            e: "\\|"
        }, {
            b: /\B\?(\\\d{1,3}|\\x[A-Fa-f0-9]{1,2}|\\u[A-Fa-f0-9]{4}|\\?\S)\b/
        } ]
    }, u = {
        cN: "params",
        b: "\\(",
        e: "\\)",
        k: n
    }, c = [ a, r, o, {
        cN: "class",
        bK: "class module",
        e: "$|;",
        i: /=/,
        c: [ t.inherit(t.TM, {
            b: "[A-Za-z_]\\w*(::\\w+)*(\\?|\\!)?"
        }), {
            cN: "inheritance",
            b: "<\\s*",
            c: [ {
                cN: "parent",
                b: "(" + t.IR + "::)?" + t.IR
            } ]
        }, o ]
    }, {
        cN: "function",
        bK: "def",
        e: " |$|;",
        r: 0,
        c: [ t.inherit(t.TM, {
            b: e
        }), u, o ]
    }, {
        cN: "constant",
        b: "(::)?(\\b[A-Z]\\w*(::)?)+",
        r: 0
    }, {
        cN: "symbol",
        b: t.UIR + "(\\!|\\?)?:",
        r: 0
    }, {
        cN: "symbol",
        b: ":",
        c: [ a, {
            b: e
        } ],
        r: 0
    }, {
        cN: "number",
        b: "(\\b0[0-7_]+)|(\\b0x[0-9a-fA-F_]+)|(\\b[1-9][0-9_]*(\\.[0-9_]+)?)|[0_]\\b",
        r: 0
    }, {
        cN: "variable",
        b: "(\\$\\W)|((\\$|\\@\\@?)(\\w+))"
    }, {
        b: "(" + t.RSR + ")\\s*",
        c: [ r, o, {
            cN: "regexp",
            c: [ t.BE, s ],
            i: /\n/,
            v: [ {
                b: "/",
                e: "/[a-z]*"
            }, {
                b: "%r{",
                e: "}[a-z]*"
            }, {
                b: "%r\\(",
                e: "\\)[a-z]*"
            }, {
                b: "%r!",
                e: "![a-z]*"
            }, {
                b: "%r\\[",
                e: "\\][a-z]*"
            } ]
        } ],
        r: 0
    } ];
    s.c = c, u.c = c;
    var l = [ {
        b: /^\s*=>/,
        cN: "status",
        starts: {
            e: "$",
            c: c
        }
    }, {
        cN: "prompt",
        b: /^\S[^=>\n]*>+/,
        starts: {
            e: "$",
            c: c
        }
    } ];
    return {
        aliases: [ "rb", "gemspec", "podspec", "thor", "irb" ],
        k: n,
        c: [ o ].concat(l).concat(c)
    };
}), hljs.registerLanguage("diff", function(t) {
    return {
        aliases: [ "patch" ],
        c: [ {
            cN: "chunk",
            r: 10,
            v: [ {
                b: /^\@\@ +\-\d+,\d+ +\+\d+,\d+ +\@\@$/
            }, {
                b: /^\*\*\* +\d+,\d+ +\*\*\*\*$/
            }, {
                b: /^\-\-\- +\d+,\d+ +\-\-\-\-$/
            } ]
        }, {
            cN: "header",
            v: [ {
                b: /Index: /,
                e: /$/
            }, {
                b: /=====/,
                e: /=====$/
            }, {
                b: /^\-\-\-/,
                e: /$/
            }, {
                b: /^\*{3} /,
                e: /$/
            }, {
                b: /^\+\+\+/,
                e: /$/
            }, {
                b: /\*{5}/,
                e: /\*{5}$/
            } ]
        }, {
            cN: "addition",
            b: "^\\+",
            e: "$"
        }, {
            cN: "deletion",
            b: "^\\-",
            e: "$"
        }, {
            cN: "change",
            b: "^\\!",
            e: "$"
        } ]
    };
}), hljs.registerLanguage("javascript", function(t) {
    return {
        aliases: [ "js" ],
        k: {
            keyword: "in if for while finally var new function do return void else break catch instanceof with throw case default try this switch continue typeof delete let yield const class",
            literal: "true false null undefined NaN Infinity",
            built_in: "eval isFinite isNaN parseFloat parseInt decodeURI decodeURIComponent encodeURI encodeURIComponent escape unescape Object Function Boolean Error EvalError InternalError RangeError ReferenceError StopIteration SyntaxError TypeError URIError Number Math Date String RegExp Array Float32Array Float64Array Int16Array Int32Array Int8Array Uint16Array Uint32Array Uint8Array Uint8ClampedArray ArrayBuffer DataView JSON Intl arguments require module console window document"
        },
        c: [ {
            cN: "pi",
            b: /^\s*('|")use strict('|")/,
            r: 10
        }, t.ASM, t.QSM, t.CLCM, t.CBCM, t.CNM, {
            b: "(" + t.RSR + "|\\b(case|return|throw)\\b)\\s*",
            k: "return throw case",
            c: [ t.CLCM, t.CBCM, t.RM, {
                b: /</,
                e: />;/,
                r: 0,
                sL: "xml"
            } ],
            r: 0
        }, {
            cN: "function",
            bK: "function",
            e: /\{/,
            eE: !0,
            c: [ t.inherit(t.TM, {
                b: /[A-Za-z$_][0-9A-Za-z$_]*/
            }), {
                cN: "params",
                b: /\(/,
                e: /\)/,
                c: [ t.CLCM, t.CBCM ],
                i: /["'\(]/
            } ],
            i: /\[|%/
        }, {
            b: /\$[(.]/
        }, {
            b: "\\." + t.IR,
            r: 0
        } ]
    };
}), hljs.registerLanguage("xml", function(t) {
    var e = "[A-Za-z0-9\\._:-]+", n = {
        b: /<\?(php)?(?!\w)/,
        e: /\?>/,
        sL: "php",
        subLanguageMode: "continuous"
    }, i = {
        eW: !0,
        i: /</,
        r: 0,
        c: [ n, {
            cN: "attribute",
            b: e,
            r: 0
        }, {
            b: "=",
            r: 0,
            c: [ {
                cN: "value",
                v: [ {
                    b: /"/,
                    e: /"/
                }, {
                    b: /'/,
                    e: /'/
                }, {
                    b: /[^\s\/>]+/
                } ]
            } ]
        } ]
    };
    return {
        aliases: [ "html", "xhtml", "rss", "atom", "xsl", "plist" ],
        cI: !0,
        c: [ {
            cN: "doctype",
            b: "<!DOCTYPE",
            e: ">",
            r: 10,
            c: [ {
                b: "\\[",
                e: "\\]"
            } ]
        }, {
            cN: "comment",
            b: "<!--",
            e: "-->",
            r: 10
        }, {
            cN: "cdata",
            b: "<\\!\\[CDATA\\[",
            e: "\\]\\]>",
            r: 10
        }, {
            cN: "tag",
            b: "<style(?=\\s|>|$)",
            e: ">",
            k: {
                title: "style"
            },
            c: [ i ],
            starts: {
                e: "</style>",
                rE: !0,
                sL: "css"
            }
        }, {
            cN: "tag",
            b: "<script(?=\\s|>|$)",
            e: ">",
            k: {
                title: "script"
            },
            c: [ i ],
            starts: {
                e: "</script>",
                rE: !0,
                sL: "javascript"
            }
        }, {
            b: "<%",
            e: "%>",
            sL: "vbscript"
        }, n, {
            cN: "pi",
            b: /<\?\w+/,
            e: /\?>/,
            r: 10
        }, {
            cN: "tag",
            b: "</?",
            e: "/?>",
            c: [ {
                cN: "title",
                b: /[^ \/><\n\t]+/,
                r: 0
            }, i ]
        } ]
    };
}), hljs.registerLanguage("markdown", function(t) {
    return {
        aliases: [ "md", "mkdown", "mkd" ],
        c: [ {
            cN: "header",
            v: [ {
                b: "^#{1,6}",
                e: "$"
            }, {
                b: "^.+?\\n[=-]{2,}$"
            } ]
        }, {
            b: "<",
            e: ">",
            sL: "xml",
            r: 0
        }, {
            cN: "bullet",
            b: "^([*+-]|(\\d+\\.))\\s+"
        }, {
            cN: "strong",
            b: "[*_]{2}.+?[*_]{2}"
        }, {
            cN: "emphasis",
            v: [ {
                b: "\\*.+?\\*"
            }, {
                b: "_.+?_",
                r: 0
            } ]
        }, {
            cN: "blockquote",
            b: "^>\\s+",
            e: "$"
        }, {
            cN: "code",
            v: [ {
                b: "`.+?`"
            }, {
                b: "^( {4}|	)",
                e: "$",
                r: 0
            } ]
        }, {
            cN: "horizontal_rule",
            b: "^[-\\*]{3,}",
            e: "$"
        }, {
            b: "\\[.+?\\][\\(\\[].*?[\\)\\]]",
            rB: !0,
            c: [ {
                cN: "link_label",
                b: "\\[",
                e: "\\]",
                eB: !0,
                rE: !0,
                r: 0
            }, {
                cN: "link_url",
                b: "\\]\\(",
                e: "\\)",
                eB: !0,
                eE: !0
            }, {
                cN: "link_reference",
                b: "\\]\\[",
                e: "\\]",
                eB: !0,
                eE: !0
            } ],
            r: 10
        }, {
            b: "^\\[.+\\]:",
            rB: !0,
            c: [ {
                cN: "link_reference",
                b: "\\[",
                e: "\\]:",
                eB: !0,
                eE: !0,
                starts: {
                    cN: "link_url",
                    e: "$"
                }
            } ]
        } ]
    };
}), hljs.registerLanguage("css", function(t) {
    var e = "[a-zA-Z-][a-zA-Z0-9_-]*", n = {
        cN: "function",
        b: e + "\\(",
        rB: !0,
        eE: !0,
        e: "\\("
    };
    return {
        cI: !0,
        i: "[=/|']",
        c: [ t.CBCM, {
            cN: "id",
            b: "\\#[A-Za-z0-9_-]+"
        }, {
            cN: "class",
            b: "\\.[A-Za-z0-9_-]+",
            r: 0
        }, {
            cN: "attr_selector",
            b: "\\[",
            e: "\\]",
            i: "$"
        }, {
            cN: "pseudo",
            b: ":(:)?[a-zA-Z0-9\\_\\-\\+\\(\\)\\\"\\']+"
        }, {
            cN: "at_rule",
            b: "@(font-face|page)",
            l: "[a-z-]+",
            k: "font-face page"
        }, {
            cN: "at_rule",
            b: "@",
            e: "[{;]",
            c: [ {
                cN: "keyword",
                b: /\S+/
            }, {
                b: /\s/,
                eW: !0,
                eE: !0,
                r: 0,
                c: [ n, t.ASM, t.QSM, t.CSSNM ]
            } ]
        }, {
            cN: "tag",
            b: e,
            r: 0
        }, {
            cN: "rules",
            b: "{",
            e: "}",
            i: "[^\\s]",
            r: 0,
            c: [ t.CBCM, {
                cN: "rule",
                b: "[^\\s]",
                rB: !0,
                e: ";",
                eW: !0,
                c: [ {
                    cN: "attribute",
                    b: "[A-Z\\_\\.\\-]+",
                    e: ":",
                    eE: !0,
                    i: "[^\\s]",
                    starts: {
                        cN: "value",
                        eW: !0,
                        eE: !0,
                        c: [ n, t.CSSNM, t.QSM, t.ASM, t.CBCM, {
                            cN: "hexcolor",
                            b: "#[0-9A-Fa-f]+"
                        }, {
                            cN: "important",
                            b: "!important"
                        } ]
                    }
                } ]
            } ]
        } ]
    };
}), hljs.registerLanguage("java", function(t) {
    var e = t.UIR + "(<" + t.UIR + ">)?", n = "false synchronized int abstract float private char boolean static null if const for true while long throw strictfp finally protected import native final return void enum else break transient new catch instanceof byte super volatile case assert short package default double public try this switch continue throws protected public private";
    return {
        aliases: [ "jsp" ],
        k: n,
        i: /<\//,
        c: [ {
            cN: "javadoc",
            b: "/\\*\\*",
            e: "\\*/",
            r: 0,
            c: [ {
                cN: "javadoctag",
                b: "(^|\\s)@[A-Za-z]+"
            } ]
        }, t.CLCM, t.CBCM, t.ASM, t.QSM, {
            cN: "class",
            bK: "class interface",
            e: /[{;=]/,
            eE: !0,
            k: "class interface",
            i: /[:"\[\]]/,
            c: [ {
                bK: "extends implements"
            }, t.UTM ]
        }, {
            bK: "new",
            e: /\s/,
            r: 0
        }, {
            cN: "function",
            b: "(" + e + "\\s+)+" + t.UIR + "\\s*\\(",
            rB: !0,
            e: /[{;=]/,
            eE: !0,
            k: n,
            c: [ {
                b: t.UIR + "\\s*\\(",
                rB: !0,
                c: [ t.UTM ]
            }, {
                cN: "params",
                b: /\(/,
                e: /\)/,
                k: n,
                c: [ t.ASM, t.QSM, t.CNM, t.CBCM ]
            }, t.CLCM, t.CBCM ]
        }, t.CNM, {
            cN: "annotation",
            b: "@[A-Za-z]+"
        } ]
    };
}), hljs.registerLanguage("python", function(t) {
    var e = {
        cN: "prompt",
        b: /^(>>>|\.\.\.) /
    }, n = {
        cN: "string",
        c: [ t.BE ],
        v: [ {
            b: /(u|b)?r?'''/,
            e: /'''/,
            c: [ e ],
            r: 10
        }, {
            b: /(u|b)?r?"""/,
            e: /"""/,
            c: [ e ],
            r: 10
        }, {
            b: /(u|r|ur)'/,
            e: /'/,
            r: 10
        }, {
            b: /(u|r|ur)"/,
            e: /"/,
            r: 10
        }, {
            b: /(b|br)'/,
            e: /'/
        }, {
            b: /(b|br)"/,
            e: /"/
        }, t.ASM, t.QSM ]
    }, i = {
        cN: "number",
        r: 0,
        v: [ {
            b: t.BNR + "[lLjJ]?"
        }, {
            b: "\\b(0o[0-7]+)[lLjJ]?"
        }, {
            b: t.CNR + "[lLjJ]?"
        } ]
    }, r = {
        cN: "params",
        b: /\(/,
        e: /\)/,
        c: [ "self", e, i, n ]
    }, o = {
        e: /:/,
        i: /[${=;\n]/,
        c: [ t.UTM, r ]
    };
    return {
        aliases: [ "py", "gyp" ],
        k: {
            keyword: "and elif is global as in if from raise for except finally print import pass return exec else break not with class assert yield try while continue del or def lambda nonlocal|10 None True False",
            built_in: "Ellipsis NotImplemented"
        },
        i: /(<\/|->|\?)/,
        c: [ e, i, n, t.HCM, t.inherit(o, {
            cN: "function",
            bK: "def",
            r: 10
        }), t.inherit(o, {
            cN: "class",
            bK: "class"
        }), {
            cN: "decorator",
            b: /@/,
            e: /$/
        }, {
            b: /\b(print|exec)\(/
        } ]
    };
}), hljs.registerLanguage("sql", function(t) {
    var e = {
        cN: "comment",
        b: "--",
        e: "$"
    };
    return {
        cI: !0,
        i: /[<>]/,
        c: [ {
            cN: "operator",
            bK: "begin end start commit rollback savepoint lock alter create drop rename call delete do handler insert load replace select truncate update set show pragma grant merge describe use explain help declare prepare execute deallocate savepoint release unlock purge reset change stop analyze cache flush optimize repair kill install uninstall checksum restore check backup",
            e: /;/,
            eW: !0,
            k: {
                keyword: "keyword",
                literal: "true false null",
                built_in: "array bigint binary bit blob boolean char character date dec decimal float int integer interval number numeric real serial smallint varchar varying int8 serial8 text"
            },
            c: [ {
                cN: "string",
                b: "'",
                e: "'",
                c: [ t.BE, {
                    b: "''"
                } ]
            }, {
                cN: "string",
                b: '"',
                e: '"',
                c: [ t.BE, {
                    b: '""'
                } ]
            }, {
                cN: "string",
                b: "`",
                e: "`",
                c: [ t.BE ]
            }, t.CNM, t.CBCM, e ]
        }, t.CBCM, e ]
    };
}), hljs.registerLanguage("scala", function(t) {
    var e = {
        cN: "annotation",
        b: "@[A-Za-z]+"
    }, n = {
        cN: "string",
        b: 'u?r?"""',
        e: '"""',
        r: 10
    }, i = {
        cN: "symbol",
        b: "'\\w[\\w\\d_]*(?!')"
    }, r = {
        cN: "type",
        b: "\\b[A-Z][A-Za-z0-9_]*",
        r: 0
    }, o = {
        cN: "title",
        b: /[^0-9\n\t "'(),.`{}\[\]:;][^\n\t "'(),.`{}\[\]:;]+|[^0-9\n\t "'(),.`{}\[\]:;=]/,
        r: 0
    }, s = {
        cN: "class",
        bK: "class object trait type",
        e: /[:={\[(\n;]/,
        c: [ {
            cN: "keyword",
            bK: "extends with",
            r: 10
        }, o ]
    }, a = {
        cN: "function",
        bK: "def val",
        e: /[:={\[(\n;]/,
        c: [ o ]
    };
    return {
        k: {
            literal: "true false null",
            keyword: "type yield lazy override def with val var sealed abstract private trait object if forSome for while throw finally protected extends import final return else break new catch super class case package default try this match continue throws implicit"
        },
        c: [ t.CLCM, t.CBCM, n, t.QSM, i, r, a, s, t.CNM, e ]
    };
}), hljs.registerLanguage("coffeescript", function(t) {
    var e = {
        keyword: "in if for while finally new do return else break catch instanceof throw try this switch continue typeof delete debugger super then unless until loop of by when and or is isnt not",
        literal: "true false null undefined yes no on off",
        reserved: "case default function var void with const let enum export import native __hasProp __extends __slice __bind __indexOf",
        built_in: "npm require console print module global window document"
    }, n = "[A-Za-z$_][0-9A-Za-z$_]*", i = t.inherit(t.TM, {
        b: n
    }), r = {
        cN: "subst",
        b: /#\{/,
        e: /}/,
        k: e
    }, o = [ t.BNM, t.inherit(t.CNM, {
        starts: {
            e: "(\\s*/)?",
            r: 0
        }
    }), {
        cN: "string",
        v: [ {
            b: /'''/,
            e: /'''/,
            c: [ t.BE ]
        }, {
            b: /'/,
            e: /'/,
            c: [ t.BE ]
        }, {
            b: /"""/,
            e: /"""/,
            c: [ t.BE, r ]
        }, {
            b: /"/,
            e: /"/,
            c: [ t.BE, r ]
        } ]
    }, {
        cN: "regexp",
        v: [ {
            b: "///",
            e: "///",
            c: [ r, t.HCM ]
        }, {
            b: "//[gim]*",
            r: 0
        }, {
            b: /\/(?![ *])(\\\/|.)*?\/[gim]*(?=\W|$)/
        } ]
    }, {
        cN: "property",
        b: "@" + n
    }, {
        b: "`",
        e: "`",
        eB: !0,
        eE: !0,
        sL: "javascript"
    } ];
    return r.c = o, {
        aliases: [ "coffee", "cson", "iced" ],
        k: e,
        i: /\/\*/,
        c: o.concat([ {
            cN: "comment",
            b: "###",
            e: "###"
        }, t.HCM, {
            cN: "function",
            b: "(^\\s*|\\B)(" + n + "\\s*=\\s*)?(\\(.*\\))?\\s*\\B[-=]>",
            e: "[-=]>",
            rB: !0,
            c: [ i, {
                cN: "params",
                b: "\\([^\\(]",
                rB: !0,
                c: [ {
                    b: /\(/,
                    e: /\)/,
                    k: e,
                    c: [ "self" ].concat(o)
                } ]
            } ]
        }, {
            cN: "class",
            bK: "class",
            e: "$",
            i: /[:="\[\]]/,
            c: [ {
                bK: "extends",
                eW: !0,
                i: /[:="\[\]]/,
                c: [ i ]
            }, i ]
        }, {
            cN: "attribute",
            b: n + ":",
            e: ":",
            rB: !0,
            eE: !0,
            r: 0
        } ])
    };
}), hljs.registerLanguage("nginx", function(t) {
    var e = {
        cN: "variable",
        v: [ {
            b: /\$\d+/
        }, {
            b: /\$\{/,
            e: /}/
        }, {
            b: "[\\$\\@]" + t.UIR
        } ]
    }, n = {
        eW: !0,
        l: "[a-z/_]+",
        k: {
            built_in: "on off yes no true false none blocked debug info notice warn error crit select break last permanent redirect kqueue rtsig epoll poll /dev/poll"
        },
        r: 0,
        i: "=>",
        c: [ t.HCM, {
            cN: "string",
            c: [ t.BE, e ],
            v: [ {
                b: /"/,
                e: /"/
            }, {
                b: /'/,
                e: /'/
            } ]
        }, {
            cN: "url",
            b: "([a-z]+):/",
            e: "\\s",
            eW: !0,
            eE: !0,
            c: [ e ]
        }, {
            cN: "regexp",
            c: [ t.BE, e ],
            v: [ {
                b: "\\s\\^",
                e: "\\s|{|;",
                rE: !0
            }, {
                b: "~\\*?\\s+",
                e: "\\s|{|;",
                rE: !0
            }, {
                b: "\\*(\\.[a-z\\-]+)+"
            }, {
                b: "([a-z\\-]+\\.)+\\*"
            } ]
        }, {
            cN: "number",
            b: "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(:\\d{1,5})?\\b"
        }, {
            cN: "number",
            b: "\\b\\d+[kKmMgGdshdwy]*\\b",
            r: 0
        }, e ]
    };
    return {
        aliases: [ "nginxconf" ],
        c: [ t.HCM, {
            b: t.UIR + "\\s",
            e: ";|{",
            rB: !0,
            c: [ {
                cN: "title",
                b: t.UIR,
                starts: n
            } ],
            r: 0
        } ],
        i: "[^\\s\\}]"
    };
}), hljs.registerLanguage("json", function(t) {
    var e = {
        literal: "true false null"
    }, n = [ t.QSM, t.CNM ], i = {
        cN: "value",
        e: ",",
        eW: !0,
        eE: !0,
        c: n,
        k: e
    }, r = {
        b: "{",
        e: "}",
        c: [ {
            cN: "attribute",
            b: '\\s*"',
            e: '"\\s*:\\s*',
            eB: !0,
            eE: !0,
            c: [ t.BE ],
            i: "\\n",
            starts: i
        } ],
        i: "\\S"
    }, o = {
        b: "\\[",
        e: "\\]",
        c: [ t.inherit(i, {
            cN: null
        }) ],
        i: "\\S"
    };
    return n.splice(n.length, 0, r, o), {
        c: n,
        k: e,
        i: "\\S"
    };
}), hljs.registerLanguage("makefile", function(t) {
    var e = {
        cN: "variable",
        b: /\$\(/,
        e: /\)/,
        c: [ t.BE ]
    };
    return {
        aliases: [ "mk", "mak" ],
        c: [ t.HCM, {
            b: /^\w+\s*\W*=/,
            rB: !0,
            r: 0,
            starts: {
                cN: "constant",
                e: /\s*\W*=/,
                eE: !0,
                starts: {
                    e: /$/,
                    r: 0,
                    c: [ e ]
                }
            }
        }, {
            cN: "title",
            b: /^[\w]+:\s*$/
        }, {
            cN: "phony",
            b: /^\.PHONY:/,
            e: /$/,
            k: ".PHONY",
            l: /[\.\w]+/
        }, {
            b: /^\t+/,
            e: /$/,
            r: 0,
            c: [ t.QSM, e ]
        } ]
    };
}), function() {
    var t = {
        version: "v2.0.7"
    };
    t.observable = function(t) {
        t = t || {};
        var e = {};
        return t.on = function(n, i) {
            return "function" == typeof i && n.replace(/\S+/g, function(t, n) {
                (e[t] = e[t] || []).push(i), i.typed = n > 0;
            }), t;
        }, t.off = function(n, i) {
            if ("*" == n) e = {}; else if (i) for (var r, o = e[n], s = 0; r = o && o[s]; ++s) r == i && (o.splice(s, 1),
                s--); else n.replace(/\S+/g, function(t) {
                e[t] = [];
            });
            return t;
        }, t.one = function(e, n) {
            return n && (n.one = 1), t.on(e, n);
        }, t.trigger = function(n) {
            for (var i, r = [].slice.call(arguments, 1), o = e[n] || [], s = 0; i = o[s]; ++s) i.busy || (i.busy = 1,
                i.apply(t, i.typed ? [ n ].concat(r) : r), i.one ? (o.splice(s, 1), s--) : o[s] !== i && s--,
                i.busy = 0);
            return t;
        }, t;
    }, function(t, e) {
        function n() {
            return o.hash.slice(1);
        }
        function i(t) {
            return t.split("/");
        }
        function r(t) {
            t.type && (t = n()), t != a && (s.trigger.apply(null, [ "H" ].concat(i(t))), a = t);
        }
        if (this.top) {
            var o = location, s = t.observable(), a = n(), u = window, c = t.route = function(t) {
                t[0] ? (o.hash = t, r(t)) : s.on("H", t);
            };
            c.exec = function(t) {
                t.apply(null, i(n()));
            }, c.parser = function(t) {
                i = t;
            }, u.addEventListener ? u.addEventListener(e, r, !1) : u.attachEvent("on" + e, r);
        }
    }(t, "hashchange"), t._tmpl = function() {
        function t(t, n) {
            return n = (t || "{}").replace(/\\{/g, "￰").replace(/\\}/g, "￱").split(/({[\s\S]*?})/),
                new Function("d", "return " + (n[0] || n[2] ? "[" + n.map(function(t, n) {
                        return n % 2 ? e(t, 1) : '"' + t.replace(/\n/g, "\\n").replace(/"/g, '\\"') + '"';
                    }).join(",") + '].join("")' : e(n[1])).replace(/\uFFF0/g, "{").replace(/\uFFF1/g, "}"));
        }
        function e(t, e) {
            return t = t.replace(/\n/g, " ").replace(/^[{ ]+|[ }]+$|\/\*.+?\*\//g, ""), /^\s*[\w-"']+ *:/.test(t) ? "[" + t.replace(/\W*([\w-]+)\W*:([^,]+)/g, function(t, i, r) {
                return r.replace(/\w[^,|& ]*/g, function(t) {
                        return n(t, e);
                    }) + '?"' + i + '":"",';
            }) + '].join(" ")' : n(t, e);
        }
        function n(t, e) {
            return "(function(v){try{v=" + (t.replace(r, function(t, e, n) {
                    return n ? "d." + n : t;
                }) || "x") + "}finally{return " + (e ? '!v&&v!==0?"":v' : "v") + "}}).call(d)";
        }
        var i = {}, r = /("|').+?[^\\]\1|\.\w*|\w*:|\b(?:this|true|false|null|undefined|new|typeof|Number|String|Object|Array|Math|Date|JSON)\b|([a-z_]\w*)/gi;
        return function(e, n) {
            return e && (i[e] = i[e] || t(e))(n);
        };
    }(), function(t, e) {
        function n(t, e) {
            for (var n = 0; n < (t || []).length; n++) e(t[n], n) === !1 && n--;
        }
        function i(t, e) {
            return e && Object.keys(e).map(function(n) {
                t[n] = e[n];
            }), t;
        }
        function r(t, e) {
            return t.filter(function(t) {
                return e.indexOf(t) < 0;
            });
        }
        function o(t, e) {
            for (t = e(t) === !1 ? t.nextSibling : t.firstChild; t; ) o(t, e), t = t.nextSibling;
        }
        function s(t) {
            var e = t.trim().slice(1, 3).toLowerCase(), n = /td|th/.test(e) ? "tr" : "tr" == e ? "tbody" : "div";
            return el = p.createElement(n), el.innerHTML = t, el;
        }
        function a(t, e) {
            e.trigger("update"), n(t, function(t) {
                function n(t) {
                    r.removeAttribute(t);
                }
                var i = t.tag, r = t.dom;
                if (t.loop) return n("each"), l(t, e);
                if (i) return i.update ? i.update() : t.tag = c({
                    tmpl: i[0],
                    fn: i[1],
                    root: r,
                    parent: e
                });
                var o = t.attr, s = h(t.expr, e);
                if (null == s && (s = ""), t.value !== s) {
                    if (t.value = s, !o) return r.nodeValue = s;
                    if ((!s && t.bool || /obj|func/.test(typeof s)) && n(o), "function" == typeof s) r[o] = function(t) {
                        t = t || window.event, t.which = t.which || t.charCode || t.keyCode, t.target = t.target || t.srcElement,
                            t.currentTarget = r, t.item = e.__item || e, s.call(e, t) !== !0 && (t.preventDefault && t.preventDefault(),
                            t.returnValue = !1), e.update();
                    }; else if (/^(show|hide|if)$/.test(o)) n(o), "hide" == o && (s = !s), r.style.display = s ? "" : "none"; else {
                        if (t.bool) {
                            if (r[o] = s, !s) return;
                            s = o;
                        }
                        r.setAttribute(o, s);
                    }
                }
            }), e.trigger("updated");
        }
        function u(t) {
            function e(t, e, n) {
                if (e ? e.indexOf("{") >= 0 : n) {
                    var r = {
                        dom: t,
                        expr: e
                    };
                    s.push(i(r, n || {}));
                }
            }
            var r = {}, s = [];
            return o(t, function(t) {
                var i = t.nodeType, o = t.nodeValue;
                if (3 == i && "STYLE" != t.parentNode.tagName) e(t, o); else if (1 == i) {
                    if (o = t.getAttribute("each")) return e(t, o, {
                        loop: 1
                    }), !1;
                    var s = d[t.tagName.toLowerCase()];
                    n(t.attributes, function(n) {
                        var i = n.name, o = n.value;
                        if (/^(name|id)$/.test(i) && (r[o] = t), !s) {
                            var a = i.split("__")[1];
                            if (e(t, o, {
                                    attr: a || i,
                                    bool: a
                                }), a) return t.removeAttribute(i), !1;
                        }
                    }), s && e(t, 0, {
                        tag: s
                    });
                }
            }), {
                expr: s,
                elem: r
            };
        }
        function c(e) {
            function r() {
                Object.keys(v).map(function(t) {
                    var e = o[t] = h(v[t], d || g);
                    "object" == typeof e && l.removeAttribute(t);
                });
            }
            var o = e.opts || {}, c = s(e.tmpl), l = e.root, d = e.parent, m = u(c), g = {
                root: l,
                opts: o,
                parent: d,
                __item: e.item
            }, v = {};
            for (i(g, m.elem), n(l.attributes, function(t) {
                v[t.name] = t.value;
            }), r(), g.on || (t.observable(g), delete g.off), e.fn && e.fn.call(g, o), g.update = function(t, e) {
                return d && c && !c.firstChild && (l = d.root, c = null), e || p.body.contains(l) ? (i(g, t),
                    i(g, g.__item), r(), a(m.expr, g), !e && g.__item && d.update(), !0) : void g.trigger("unmount");
            }, g.update(0, !0); c.firstChild; ) e.before ? l.insertBefore(c.firstChild, e.before) : l.appendChild(c.firstChild);
            return g.trigger("mount"), f.push(g), g;
        }
        function l(t, e) {
            function n() {
                return Array.prototype.indexOf.call(u.childNodes, a) + 1;
            }
            if (!t.done) {
                t.done = !0;
                var i, o, s = t.dom, a = s.previousSibling, u = s.parentNode, l = s.outerHTML, f = t.expr, d = f.split(/\s+in\s+/), p = [];
                d[1] && (f = "{ " + d[1], o = d[0].slice(1).trim().split(/,\s*/)), e.one("mount", function() {
                    var t = s.parentNode;
                    t && (u = t, u.removeChild(s));
                }), e.on("updated", function() {
                    var t = h(f, e);
                    if (is_array = Array.isArray(t), is_array) t = t.slice(0); else {
                        if (!t) return;
                        var s = JSON.stringify(t);
                        if (s == i) return;
                        i = s, t = Object.keys(t).map(function(e, n) {
                            var i = {};
                            return i[o[0]] = e, i[o[1]] = t[e], i;
                        });
                    }
                    r(p, t).map(function(t) {
                        var e = p.indexOf(t);
                        u.removeChild(u.childNodes[n() + e]), p.splice(e, 1);
                    }), r(t, p).map(function(r, s) {
                        var a = t.indexOf(r);
                        if (o && !i) {
                            var h = {};
                            h[o[0]] = r, h[o[1]] = a, r = h;
                        }
                        var f = c({
                            before: u.childNodes[n() + a],
                            parent: e,
                            tmpl: l,
                            item: r,
                            root: u
                        });
                        e.on("update", function() {
                            f.update(0, !0);
                        });
                    }), p = t;
                });
            }
        }
        if (e) {
            var h = t._tmpl, f = [], d = {}, p = document;
            t.tag = function(t, e, n) {
                n = n || noop, d[t] = [ e, n ];
            }, t.mountTo = function(t, e, n) {
                var i = d[e];
                return i && c({
                        tmpl: i[0],
                        fn: i[1],
                        root: t,
                        opts: n
                    });
            }, t.mount = function(e, i) {
                "*" == e && (e = Object.keys(d).join(", "));
                var r = [];
                return n(p.querySelectorAll(e), function(e) {
                    if (!e.riot) {
                        var n = e.tagName.toLowerCase(), o = t.mountTo(e, n, i);
                        o && (r.push(o), e.riot = 1);
                    }
                }), r;
            }, t.update = function() {
                return f = f.filter(function(t) {
                    return !!t.update();
                });
            };
        }
    }(t, this.top), "object" == typeof exports ? module.exports = t : "function" == typeof define && define.amd ? define(function() {
        return t;
    }) : this.riot = t;
}();

var optimizelyTrackEvent = function(t) {
    window.optimizely = window.optimizely || [], window.optimizely.push([ "trackEvent", t ]);
};
!function(t) {
    "function" == typeof define && define.amd ? define([ "jquery" ], t) : t("object" == typeof exports ? require("jquery") : jQuery);
}(function(t) {
    function e(t) {
        return a.raw ? t : encodeURIComponent(t);
    }
    function n(t) {
        return a.raw ? t : decodeURIComponent(t);
    }
    function i(t) {
        return e(a.json ? JSON.stringify(t) : String(t));
    }
    function r(t) {
        0 === t.indexOf('"') && (t = t.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, "\\"));
        try {
            return t = decodeURIComponent(t.replace(s, " ")), a.json ? JSON.parse(t) : t;
        } catch (e) {}
    }
    function o(e, n) {
        var i = a.raw ? e : r(e);
        return t.isFunction(n) ? n(i) : i;
    }
    var s = /\+/g, a = t.cookie = function(r, s, u) {
        if (void 0 !== s && !t.isFunction(s)) {
            if (u = t.extend({}, a.defaults, u), "number" == typeof u.expires) {
                var c = u.expires, l = u.expires = new Date();
                l.setTime(+l + 864e5 * c);
            }
            return document.cookie = [ e(r), "=", i(s), u.expires ? "; expires=" + u.expires.toUTCString() : "", u.path ? "; path=" + u.path : "", u.domain ? "; domain=" + u.domain : "", u.secure ? "; secure" : "" ].join("");
        }
        for (var h = r ? void 0 : {}, f = document.cookie ? document.cookie.split("; ") : [], d = 0, p = f.length; p > d; d++) {
            var m = f[d].split("="), g = n(m.shift()), v = m.join("=");
            if (r && r === g) {
                h = o(v, s);
                break;
            }
            r || void 0 === (v = o(v)) || (h[g] = v);
        }
        return h;
    };
    a.defaults = {}, t.removeCookie = function(e, n) {
        return void 0 === t.cookie(e) ? !1 : (t.cookie(e, "", t.extend({}, n, {
            expires: -1
        })), !t.cookie(e));
    };
}), Retina = function() {
    return {
        init: function() {
            var t = window.devicePixelRatio ? window.devicePixelRatio : 1;
            t > 1 && $("img").each(function(t, e) {
                e = $(e), e.attr("data-src2x") && (e.attr("data-src-orig", e.attr("src")), e.attr("src", e.attr("data-src2x")));
            });
        }
    };
}(), Retina.init()
!function(t) {
    "function" == typeof define && define.amd ? define([ "jquery" ], t) : t("object" == typeof exports ? require("jquery") : jQuery);
}(function(t) {
    function e(t) {
        return a.raw ? t : encodeURIComponent(t);
    }
    function n(t) {
        return a.raw ? t : decodeURIComponent(t);
    }
    function i(t) {
        return e(a.json ? JSON.stringify(t) : String(t));
    }
    function r(t) {
        0 === t.indexOf('"') && (t = t.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, "\\"));
        try {
            return t = decodeURIComponent(t.replace(s, " ")), a.json ? JSON.parse(t) : t;
        } catch (e) {}
    }
    function o(e, n) {
        var i = a.raw ? e : r(e);
        return t.isFunction(n) ? n(i) : i;
    }
    var s = /\+/g, a = t.cookie = function(r, s, u) {
        if (void 0 !== s && !t.isFunction(s)) {
            if (u = t.extend({}, a.defaults, u), "number" == typeof u.expires) {
                var c = u.expires, l = u.expires = new Date();
                l.setTime(+l + 864e5 * c);
            }
            return document.cookie = [ e(r), "=", i(s), u.expires ? "; expires=" + u.expires.toUTCString() : "", u.path ? "; path=" + u.path : "", u.domain ? "; domain=" + u.domain : "", u.secure ? "; secure" : "" ].join("");
        }
        for (var h = r ? void 0 : {}, f = document.cookie ? document.cookie.split("; ") : [], d = 0, p = f.length; p > d; d++) {
            var m = f[d].split("="), g = n(m.shift()), v = m.join("=");
            if (r && r === g) {
                h = o(v, s);
                break;
            }
            r || void 0 === (v = o(v)) || (h[g] = v);
        }
        return h;
    };
    a.defaults = {}, t.removeCookie = function(e, n) {
        return void 0 === t.cookie(e) ? !1 : (t.cookie(e, "", t.extend({}, n, {
            expires: -1
        })), !t.cookie(e));
    };
}), Retina = function() {
    return {
        init: function() {
            var t = window.devicePixelRatio ? window.devicePixelRatio : 1;
            t > 1 && $("img").each(function(t, e) {
                e = $(e), e.attr("data-src2x") && (e.attr("data-src-orig", e.attr("src")), e.attr("src", e.attr("data-src2x")));
            });
        }
    };
}(), Retina.init(), $(function() {
    "true" == getURLParameter("from_10sheet") && showWarning();
    var t = function() {
        if ($(".js-header").length > 0) {
            var t = 580, e = $(window).height();
            t + 180 > e && e > 300 && ($(".js-header").height(e - 180), $(".js-header-text").css("padding-top", (e - 180) / 2 + "px"));
        }
        if ($(".js-features-header").length > 0) {
            var t = 700, e = $(window).height();
            t > e && e > 400 && $(".js-features-header").height(e);
        }
    };
    t(), $(document).on("click", "#close_warning", function() {
        $("#close_warning").parent().parent().remove();
    }), "undefined" != typeof analytics && analytics.ready(function() {
        "undefined" != typeof mixpanel && (mixpanel.register_once("$initial_referrer", document.referrer),
            mixpanel.people.set_once("$initial_referrer", document.referrer));
    });
    var e = $("html, body");
    $("a[href*=#]").click(function(t) {
        var n = $.attr(this, "href");
        e.animate({
            scrollTop: $(n).offset().top
        }, 500, function() {
            window.location.hash = n;
        }), t.preventDefault();
    });
}), $(function() {
    $("[data-share-link]").each(function() {
        var t, e = $(this);
        switch (e.data("share-link")) {
            case "facebook":
                t = "https://www.facebook.com/sharer/sharer.php?u=" + window.encodeURIComponent(window.location.href);
                break;

            case "twitter":
                t = "https://twitter.com/share?url=" + window.encodeURIComponent(window.location.href) + "&text=" + window.encodeURIComponent(document.title);
                break;

            case "linkedin":
                t = "https://www.linkedin.com/shareArticle?url=" + window.encodeURIComponent(window.location.href) + "&title=" + window.encodeURIComponent(document.title);
        }
        e.attr({
            href: t,
            target: "_blank"
        });
    });
}), $(function() {
    $(".js-adopt-orphans").each(function() {
        var t = $(this), e = 3, n = t.html().trim().split(" "), i = n.slice(0, n.length - e).concat(n.slice(-e).join("&nbsp;")).join(" ");
        $(this).html(i);
    });
}), $(function() {
    $(".js-mobile-non-interactive").click(function() {
        return !1;
    });
}), $(function() {
    $(document).on("click", ".js-click-ambassador", function() {
        $("._lsn button").click();
    });
}), $(function() {
    var t = new Date(), e = t.getHours(), n = t.getDay(), i = 6 == n || 0 == n;
    $(".call-cta-btn").click(function() {
        window.analytics.track("Clicked call");
    }), $(".book-cta-btn").click(function() {
        window.analytics.track("Clicked book call");
    }), e >= 6 && 18 > e && n != i ? $(".call-cta-btn").show() : ($(".call-cta-btn").hide(),
        $(".book-cta-btn").removeClass("hide"));
}), $(function() {
    try {
        var t = geoip_country_code();
        "US" == t ? ($(".js-US-only").show(), $(".js-CA-only").hide(), $(".js-unsupported-location").hide()) : "CA" == t ? ($(".js-US-only").hide(),
            $(".js-CA-only").show(), $(".js-unsupported-location").hide(), $("#canada-overlay").show()) : ($(".js-US-only").hide(),
            $(".js-CA-only").hide(), $(".js-unsupported-location").show()), window.localStorage && window.localStorage.setItem("customerCountry", t);
    } catch (e) {
        $(".js-US-only").show(), $(".js-CA-only").hide(), $(".js-unsupported-location").hide(),
            console.log("MaxMind API not loaded.");
    }
}), $(function() {
    if (window.matchMedia("only screen and (min-width: 48em)").matches) {
        var t = $("div.home").length, e = $(".features").length, n = $(".topbar-dark").length;
        $(document).scroll(function() {
            position = $(document).scrollTop(), position > 50 ? $(".js-header-new").addClass("header-new--background") : $(".js-header-new").removeClass("header-new--background"),
                t && position > 800 ? ($("#desktop-topbar").addClass("smallbar"), $("#desktop-topbar").addClass("header--inverse")) : (e || n) && position > 50 ? ($("#desktop-topbar").addClass("smallbar"),
                    $("#desktop-topbar").addClass("header--inverse")) : !t && position > 400 ? ($("#desktop-topbar").addClass("smallbar"),
                    $("#desktop-topbar").addClass("header--inverse")) : ($("#desktop-topbar").removeClass("smallbar"),
                    $("#desktop-topbar").removeClass("header--inverse"));
        });
    }
    $(".hamburger").click(function() {
        $(".mobile-nav").toggleClass("expanded");
    });
    var i = window.location.pathname;
    $("#tournav a").each(function(t) {
        i.toUpperCase() == $(this).attr("href").toUpperCase() && $(this).addClass("current");
    }), $("#header__linklist a").each(function(t) {
        i.toUpperCase() == $(this).attr("href").toUpperCase() && $(this).addClass("current");
    }), $(".js-mobile-navigation").click(function() {
        var t = $(".header-mobile-new");
        t.toggleClass("menu-open");
    });
}), $(function() {
    function t() {
        var t = $(window).height(), e = 575;
        t > e ? ($("#home__hero").height(t), $("#hero__copy").css("top", function() {
            return t / 4;
        })) : $("#home__hero").height(e);
    }
    $(document).scroll(function() {
        var t = $(document).scrollTop();
        t > 490 && ($(".message").addClass("bwoop"), $(".nametag").addClass("fade-in")),
        t > 4650 && $(".sparkline").addClass("loadAnimation"), t > 4650 && ($(".sparkline").addClass("loadAnimation"),
        document.getElementById("interface-video") && document.getElementById("interface-video").play());
    }), Modernizr.video.h264 || ($("#open-vimeo-link").toggle(), $("#play-intro-video").toggle()),
        $(function() {
            t(), $("#hero__copy").addClass("hero__copy--fade");
        }), window.onresize = function(e) {
        t();
    }, $("#play-intro-video").click(function() {
        $("#bookkeeper_slide_content").fadeToggle(), setTimeout(function() {
            $("#bookkeeper_slide").toggleClass("slide--text slide--video bookkeeper");
        }, 500), setTimeout(function() {
            $("#video_slide_content").fadeToggle();
        }, 500), document.getElementById("intro-video").play();
    }), $("#close-intro-video").click(function() {
        $("#bookkeeper_slide").toggleClass("slide--text slide--video bookkeeper"), $("#video_slide_content").fadeToggle(),
            setTimeout(function() {
                $("#bookkeeper_slide_content").fadeToggle();
            }, 500), document.getElementById("intro-video").pause();
    });
}), $(function() {
    function t() {
        var t = $(".pricing-chart__price"), e = $(t).width();
        $(t).css({
            height: e + "px",
            top: "-" + Math.round(e / 2) + "px",
            marginBottom: "-" + Math.round(e / 2) + "px"
        }), $(t).children(".pricing-chart__price--large").css({
            marginTop: Math.round(e / 2) - 20 + "px",
            fontSize: Math.round(e / 4)
        }), $(t).children(".pricing-chart__price--call").css({
            marginTop: Math.round(e / 2) - 14 + "px"
        });
        var n = $(".pricing-new-chart__price"), i = $(n).width();
        $(n).css({
            height: i + "px",
            marginBottom: "-" + Math.round(i / 2) + "px"
        }), $(n).children(".pricing-new-chart__price--large").css({
            marginTop: Math.round(i / 2) - 28 + "px",
            fontSize: Math.round(i / 4)
        }), $(n).children(".pricing-new-chart__price--call").css({
            marginTop: Math.round(i / 2) - 20 + "px"
        });
    }
    window.onresize = function(e) {
        t();
    }, t();
}), $(function() {
    $(".js-signup-submit").click(function(t) {
        var e = $(this).parents("form:first"), n = Ladda.create(e.find(".ladda-button").get(0));
        n.start(), e.parsley("validate") || (n.stop(), t.preventDefault()), $(".js-validation-errors").hide(),
            $(".js-validation-errors").empty();
        var i = {
            email: e.find(".js-signup-email").val(),
            password1: e.find(".js-signup-password-1").val(),
            password2: e.find(".js-signup-password-2").val()
        }, r = function() {
            $.post("/logon", {
                j_username: i.email,
                j_password: i.password1
            }, function() {
                location.href = "/app/";
            });
        }, o = $.ajax({
            type: "POST",
            url: API_URL + "/signup.json",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data: $.toJSON(i),
            timeout: 1e4
        });
        o.success(function() {
            analytics.track("Signed Up"), analytics.track("Signed Up 2"), optimizelyTrackEvent("signup"),
                setTimeout(r, 2e3);
        }), o.fail(function(t) {
            "" != t.responseText && $.each($.secureEvalJSON(t.responseText).messages, function(t, e) {
                $("#validation-errors").show(), $("#validation-errors").append('<div class="global-form-error-message"><span class="icon ss-gizmo ss-alert"></span>' + e.message + "</div>");
            }), n.stop();
        }), t.preventDefault();
    });
    var t = !1, e = [ !0, !0 ];
    $(document).scroll(function() {
        return t ? !1 : 0 === $(".cta-signup-footer").length ? !1 : void ($(this).scrollTop() >= $(".cta-signup-footer").position().top - $(window).height() / 2 && ($(".iconic-one").attr("class", $(".iconic-one").attr("class") + " animate"),
            $(".iconic-two").attr("class", $(".iconic-two").attr("class") + " animate"), $(".iconic-three").attr("class", $(".iconic-three").attr("class") + " animate"),
            $(".cta-signup-footer__dots").addClass("animate"), t = !0));
    }), $(".icon-dots--three").last().on("webkitAnimationEnd oAnimationend oAnimationEnd msAnimationEnd Animationend", function() {
        $(".cta-signup-footer__dots").removeClass("animate"), setTimeout(function() {
            $(".cta-signup-footer__dots").addClass("animate");
        }, 1e3);
    }), $(".js-toggle-password").click(function() {
        e[0] === !0 ? ($(".signup-form .password_masked").hide(), $(".signup-form .password_visible").show(),
            $(".js-toggle-password span").text("隐藏"), e[0] = !1) : ($(".signup-form .password_masked").show(),
            $(".signup-form .password_visible").hide(), $(".js-toggle-password span").text("显示"),
            e[0] = !0);
    }), $(".password_masked").keyup(function() {
        $(this).siblings(".password_visible").val($(this).val());
    }), $(".password_visible").keyup(function() {
        $(this).siblings(".password_masked").val($(this).val());
    }), $(".js-signup-error-close").click(function() {
        $(".signup-error").hide();
    }), $(".js-signup-submit-new").click(function(t) {
        if (!redirectIfMainappOffline(t)) {
            var e = $(this).parents("form:first");
            if ($(this).addClass("loading"), $(this).children("span").text("Loading"), !e.parsley("validate")) return $(this).removeClass("loading"),
                $(this).children("span").text("Get Started"), t.preventDefault(), !1;
            var n = {
                email: e.find(".js-signup-email").val(),
                password1: e.find(".js-signup-password").val(),
                password2: e.find(".js-signup-password").val()
            }, i = function() {
                $.post("/logon", {
                    j_username: n.email,
                    j_password: n.password1
                }, function() {
                    location.href = "/app/";
                });
            }, r = $.ajax({
                type: "POST",
                url: API_URL + "/signup.json",
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                data: $.toJSON(n),
                timeout: 1e4
            });
            r.success(function() {
                analytics.track("Signed Up"), analytics.track("Signed Up 2"), optimizelyTrackEvent("signup"),
                $(".new-home").length > 0 && optimizelyTrackEvent("signup-home-cta"), setTimeout(i, 2e3);
            }), r.fail(function(t) {
                t.status >= 300 && $(".signup-error").show(), $(this).removeClass("loading"), $(this).children("span").text("Get Started");
            }), t.preventDefault();
        }
    }), $(".js-signup-submit-name").click(function(t) {
        var e = $(this).closest("form");
        return e.parsley("validate") ? ($.post("#", {
            name: e.find(".js-signup-name").val(),
            email: e.find(".js-signup-email").val(),
            referrer: document.referrer,
            status: "Accountant"
        }).then(function() {
            $(".fixed-overlay").removeClass("hide"), $(".js-signup-name, .js-signup-email").val(""),
                window.analytics.track("mobile signup");
        }), t.preventDefault(), void t.stopPropagation()) : (t.preventDefault(), t.stopPropagation(),
            !1);
    }), $(".js-signup-submit-phone").click(function(t) {
        var e = $(this).closest("form");
        return e.parsley("validate") ? ($.post("#", {
            name: e.find(".js-signup-name").val(),
            phone: e.find(".js-signup-phone").val(),
            email: e.find(".js-signup-email").val(),
            referrer: document.referrer
        }).then(function() {
            $(".fixed-overlay").removeClass("hide"), $(".js-signup-name, .js-signup-phone, .js-signup-email").val(""),
                window.analytics.track("mobile signup");
        }), t.preventDefault(), void t.stopPropagation()) : (t.preventDefault(), t.stopPropagation(),
            !1);
    }), $(".js-submit-canada").click(function(t) {
        var e = $(this).closest("form");
        return e.parsley("validate") ? ($.post("#", {
            name: e.find(".js-signup-name").val(),
            email: e.find(".js-signup-email").val(),
            referrer: document.referrer,
            status: "Canadian"
        }).then(function() {
            $(".canada-overlay-form").hide(), $(".canada-overlay-thanks").show(), $(".js-signup-name, .js-signup-email").val(""),
                window.analytics.track("canadian email");
        }), t.preventDefault(), void t.stopPropagation()) : (t.preventDefault(), t.stopPropagation(),
            !1);
    });
}), $(function() {
    $(".js-reset-password-submit").click(function(t) {
        var e = $(this).parents("form:first"), n = Ladda.create(e.find(".ladda-button").get(0));
        n.start(), e.parsley("validate") || (n.stop(), t.preventDefault());
        var i = {
            password: e.find(".js-login-password").val(),
            token: getURLParameter("token")
        }, r = $.ajax({
            type: "PUT",
            url: API_URL + "/person.json/reset_password/",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data: $.toJSON(i),
            timeout: 1e4
        });
        r.success(function(t) {
            location.replace(t);
        }), r.fail(function() {
            n.stop(), toggleHide($(".js-login-error")), $(".js-login-error").html(""), $(".js-login-error").append('<div class="global-form-error-message"><span class="icon ss-gizmo ss-alert" style="padding-top: 16px"></span>Oops. Your password reset token is no longer valid.<br />You can <a href="/resetpassword">send another password reset request</a>.</div>');
        }), t.preventDefault();
    });
}), $(function() {
    if ($(".features").length > 0 && $(document).scroll(function() {
            var t = $(document).scrollTop();
            t > 300 && $(".features__accountant-chat").addClass("animate"), t > 1e3 && ($(".features__section__steps__circle").addClass("animate"),
                $(".features__section__steps__line").addClass("animate")), t > 3400 && $(".features__calendar").addClass("animate"),
            t > 2450 && $("#js-video-statement").length && $("#js-video-statement")[0].play(),
            t > 4240 && $("#js-video-upload").length && $("#js-video-upload")[0].play();
        }), $(".features__checklist li").click(function() {
            $(this).toggleClass("checked");
        }), $(".features-new").length > 0 && $(document).scroll(function() {
            var t = $(document).scrollTop();
            t > 300 && $(".features-new__accountant-chat").addClass("animate"), t > 1e3 && ($(".features-new__section__steps__circle").addClass("animate"),
                $(".features-new__section__steps__line").addClass("animate")), t > 3400 && $(".features-new__calendar").addClass("animate"),
            t > 2450 && $("#js-video-statement").length && $("#js-video-statement")[0].play(),
            t > 4240 && $("#js-video-upload").length && $("#js-video-upload")[0].play();
        }), $(".features-new__checklist li").click(function() {
            $(this).toggleClass("checked");
        }), $(".tax").length > 0 && loadTaxGuide(), $(".testimonials").length > 0) {
        var t = 1e4, e = 0, n = 0;
        $(".new-home__clients__container__half").each(function() {
            var r = 0, o = [], s = $(this);
            e++, $(this).children(".new-home__clients__client").each(function() {
                r++, $(this).hide();
                var t = $(this);
                o.push([ t, r ]), $(this).height() > n && (n = $(this).height());
            });
            var a = function(e, n) {
                setTimeout(function() {
                    e.show(), e.css({
                        y: "-40px",
                        opacity: 0
                    }), e.transition({
                        y: "0px",
                        opacity: 1
                    }), setTimeout(function() {
                        e.transition({
                            y: "40px",
                            opacity: 0
                        }, function() {
                            e.hide();
                        });
                    }, t - 500);
                }, (n - 1) * t);
            };
            setTimeout(function() {
                for (i = 0; i < o.length; i++) a(o[i][0], o[i][1]);
                setInterval(function() {
                    for (i = 0; i < o.length; i++) a(o[i][0], o[i][1]);
                }, s.children(".new-home__clients__client").length * t);
            }, (e - 1) * (t / 2), o, a, s);
        }), $(".new-home__clients__container__half").height(n);
    }
    if ($(".new-home").length > 0) {
        if ($(document).scroll(function() {
                var t = $(document).scrollTop();
                t > 720 && $(".js-animate-timeline").addClass("animate");
            }), "function" == typeof Event && SVG.supported) {
            $(".linechart__image").hide();
            var r, o, s = 0, a = function() {
                var t = $(window).width(), e = 250, n = {
                    color: "#E57958",
                    fill: "#F6F6F6",
                    space: 300,
                    animateDuration: 1e3,
                    animateEasing: function(t) {
                        return (t /= .5) < 1 ? .5 * Math.pow(t, 3) : .5 * (Math.pow(t - 2, 3) + 2);
                    }
                };
                r = SVG("linechart").size(t, e), n.totalPoints = Math.ceil(t / n.space) + 2, n.halfSpace = Math.round(n.space / 2),
                    s = n.totalPoints;
                var i = [], o = [], a = [], u = function(t, r) {
                    i = [], o = [];
                    for (var s = 0; s < n.totalPoints; s++) {
                        var a = r, u = t;
                        2 > s && (a = 200, u = 230), s === n.totalPoints - 2 && (a = 30, u = 80), s === n.totalPoints - 1 && (a = 10,
                            u = 20), i.push([ s * n.space - n.halfSpace, Math.round(Math.random() * (a - u)) + u ]);
                    }
                    o = i.slice(0), o.push([ n.totalPoints * n.space - n.halfSpace, e ], [ -n.halfSpace, e ]);
                }, c = function(t) {
                    for (var e, i = 0; i < t.length; i++) e = r.circle(16).fill(n.fill).stroke({
                        color: n.color,
                        width: 2
                    }).move(t[i][0] - 8, t[i][1] - 8), a.push(e);
                }, l = function() {
                    for (var t = 0; t < i.length; t++) a[t].animate(n.animateDuration, n.animateEasing).move(i[t][0] - 8, i[t][1] - 8);
                };
                u(50, 200);
                var h = r.gradient("linear", function(t) {
                    t.at({
                        offset: 0,
                        color: n.fill,
                        opacity: .5
                    }), t.at({
                        offset: 1,
                        color: n.fill,
                        opacity: 1
                    });
                });
                h.from(0, 0).to(0, 1);
                var f = r.polygon(o).fill(h).stroke("none"), d = r.polyline(i).fill("none").stroke({
                    width: 2,
                    color: n.color
                });
                c(i);
                var p = function() {
                    u(50, 200), f.animate(n.animateDuration, n.animateEasing).plot(o), d.animate(n.animateDuration, n.animateEasing).plot(i),
                        l();
                }, m = setTimeout(function() {
                    p();
                }, 1e3);
                $(document).scroll(function() {
                    clearTimeout(m), m = setTimeout(function() {
                        p();
                    }, 1e3);
                });
            };
            $(window).width() > 770 && (o = new a());
        }
        $(window).resize(function() {
            "function" == typeof Event && SVG.supported && ($(window).width() > 770 ? Math.ceil($(window).width() / 300) + 2 !== s ? ($("#linechart").empty(),
            "object" == typeof o && (clearInterval(o.interval), o = void 0), o = new a()) : r.size($(window).width(), 250) : $(window).width() <= 770 && ($("#linechart").empty(),
                s = 0));
        });
    }
    if ($(".developers").length > 0) {
        $(document).scroll(function() {
            var t = $(document).scrollTop();
            t > 370 && $(".developers__section__graphic--system").addClass("animate"), t > 900 && $(".developers__section__graphic--octocat").addClass("animate");
        });
        var u = [ ".fragment-one", ".fragment-two", ".fragment-three", ".fragment-four" ], c = 0;
        setInterval(function() {
            c++, c >= u.length && (c = 0);
            for (var t = 0; t <= u.length; t++) $(u[t]).fadeOut();
            $(u[c]).fadeIn();
        }, 6e3);
    }
    $(".page-404").length > 0 && ($(".page-404__container").css({
        visibility: "visible"
    }), $(".page-404__sign").hide(), $(".page-404__text").css({
        opacity: 0
    }));
}), $(window).load(function() {
    if ($(".fancy-shapes-animation").length > 0 && (navigator.userAgent.indexOf("Safari") > -1 && navigator.userAgent.indexOf("Chrome") < 0 ? $(".fancy-shapes-animation").addClass("static") : setTimeout(function() {
            $(".fancy-shapes-animation").addClass("animate");
        }, 500)), $(".js-background-replace").each(function() {
            var t = $(this).data("background-src"), e = this, n = new Image();
            n.onload = function() {
                $(e).css("background-image", "url(" + t + ")");
            }, n.src = t;
        }), $(".js-add-image").attr("src", function() {
            return $(this).data("src");
        }), $(".page-404").length > 0) {
        $(".page-404__sign").show();
        var t = function() {
            return Math.round(200 * Math.random()) - 100;
        }, e = function() {
            return Math.round(200 * Math.random()) - 100;
        }, n = function() {
            return Math.round(720 * Math.random()) - 360;
        };
        $(".page-404__shape").each(function() {
            "undefined" != typeof window.chrome ? ($(this).css({
                x: t(),
                y: e(),
                rotate: n(),
                opacity: 0,
                transformOrigin: "400px 300px"
            }), $(this).transition({
                x: 0,
                y: 0,
                rotate: 0,
                opacity: 1,
                duration: 2e3
            })) : ($(this).css({
                x: t(),
                y: e()
            }), $(this).transition({
                x: 0,
                y: 0,
                duration: 2e3
            }));
        }), setTimeout(function() {
            $(".page-404__text").transition({
                opacity: 1
            });
        }, 2200);
    }
}), $(function() {
    $("input").placeholder();
    var t = !1;
    $(".js-questions-button").click(function() {
        t || (t = !0, $(".js-signup-container").css({
            scale: 1,
            opacity: 1
        }), $(".js-signup-container").transition({
            scale: .8,
            opacity: 0
        }, function() {
            $(".js-signup-container").hide(), $(".js-signup-questions").css({
                display: "block",
                scale: 1.2,
                opacity: 0
            }), $(".js-signup-questions").transition({
                scale: 1,
                opacity: 1
            }), t = !1;
        }));
    }), $(".js-questions-back-button").click(function() {
        t || (t = !0, $(".js-signup-questions").css({
            scale: 1,
            opacity: 1
        }), $(".js-signup-questions").transition({
            scale: 1.2,
            opacity: 0
        }, function() {
            $(".js-signup-questions").hide(), $(".js-signup-container").css({
                display: "block",
                scale: .8,
                opacity: 0
            }), $(".js-signup-container").transition({
                scale: 1,
                opacity: 1
            }), t = !1;
        }));
    }), $(".js-forgot-button").click(function() {
        t || (t = !0, $(".js-login-container").css({
            scale: 1,
            opacity: 1
        }), $(".js-login-container").transition({
            scale: .8,
            opacity: 0
        }, function() {
            $(".js-login-container").addClass("hide"), $(".js-login-forgot").removeClass("hide").css({
                scale: 1.2,
                opacity: 0
            }), $(".js-login-forgot").transition({
                scale: 1,
                opacity: 1
            }), t = !1;
        }));
    }), $(".js-forgot-back-button").click(function() {
        t || (t = !0, $(".js-login-forgot").css({
            scale: 1,
            opacity: 1
        }), $(".js-login-forgot").transition({
            scale: 1.2,
            opacity: 0
        }, function() {
            $(".js-login-forgot").addClass("hide"), $(".js-login-container").removeClass("hide").css({
                scale: .8,
                opacity: 0
            }), $(".js-login-container").transition({
                scale: 1,
                opacity: 1
            }), t = !1;
        }));
    });
    var e = $(".signup-new form.account-form"), n = function(t, e) {
        $.get("/api/v1/signup.json/account_available?email=" + encodeURIComponent(t), function(t) {
            e(t.message ? !1 : !0);
        }).fail(function(t) {
            e(!1);
        });
    }, i = function(t, e, i) {
        var r = !0, o = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/, s = e.find(".js-account-form-email"), a = e.find(".js-account-form-password");
        return "" === a.find("input").val() && (a.find(".account-form__error__message--empty").show(),
            a.addClass("account-form__error"), r = !1), "" === s.find("input").val() ? (s.find(".account-form__error__message--empty").show(),
            s.addClass("account-form__error"), r = !1) : o.test(s.find("input").val()) || (s.find(".account-form__error__message--invalid").show(),
            s.addClass("account-form__error"), r = !1), r ? i ? void n(s.find("input").val(), function(e) {
            e ? t(!0) : (s.find(".account-form__error__message--exists").show(), s.addClass("account-form__error"),
                t(!1));
        }) : (t(!0), !0) : (t(!1), !1);
    };
    e.find("input").focus(function() {
        $(this).parent("label").removeClass("account-form__error"), $(this).siblings(".account-form__error__message").hide();
    });
    var r = "", o = function(t) {
        var e = t.find(".js-account-button:visible");
        "Loading" !== e.text && (r = e.text()), e.addClass("loading"), e.text("Loading");
    }, s = function(t) {
        var e = t.find(".js-account-button:visible");
        e.removeClass("loading"), e.text(r);
    };
    e.submit(function(t) {
        redirectIfMainappOffline(t) || (o(e), i(function(t) {
            if (t) {
                var n = {
                    email: e.find('input[name="j_username"]').val(),
                    password1: e.find('input[name="j_password"]').val(),
                    password2: e.find('input[name="j_password"]').val()
                }, i = function() {
                    $.post("/logon", {
                        j_username: n.email,
                        j_password: n.password1
                    }, function() {
                        location.href = "/app/";
                    });
                }, r = $.ajax({
                    type: "POST",
                    url: API_URL + "/signup.json",
                    dataType: "json",
                    contentType: "application/json; charset=UTF-8",
                    data: $.toJSON(n),
                    timeout: 1e4
                });
                r.success(function() {
                    analytics.track("Signed Up"), analytics.track("Signed Up 2"), optimizelyTrackEvent("signup"),
                    $(".new-home").length > 0 && optimizelyTrackEvent("signup-home-cta"), setTimeout(i, 2e3);
                }), r.fail(function(t) {
                    t.status >= 300 && $(".signup-error").show(), s(e);
                });
            } else s(e);
        }, e, !0), t.preventDefault());
    });
    var a = $(".js-login-container form.account-form");
    a.length > 0 && ("true" == getURLParameter("error") && $(".login-new__main__form__credentials-error").show(),
    "true" == getURLParameter("session_expired") && $(".login-new__main__form__session-expired").show(),
    "true" == getURLParameter("insufficient_security") && $(".login-new__main__form__insufficient-security").show()),
        a.find("input").focus(function() {
            $(this).parent("label").removeClass("account-form__error"), $(this).siblings(".account-form__error__message").hide();
        }), a.submit(function(t) {
        redirectIfMainappOffline(t) || (o(a), i(function() {}, a, !1) || (s(a), t.preventDefault()));
    });
    var u = $(".js-login-forgot form.account-form");
    u.submit(function(t) {
        if (!redirectIfMainappOffline(t)) {
            o(u);
            var e = $.ajax({
                type: "POST",
                url: API_URL + "/person.json/reset_password/" + encodeURIComponent(u.find('input[name="j_username"]').val()),
                timeout: 1e4
            });
            e.success(function() {
                $(".login-new__main__form__forgot--success").show(), s(u), analytics.track("Password Reset");
            }), e.fail(function() {
                $(".login-new__main__form__forgot--error").show(), s(u);
            }), t.preventDefault();
        }
    });
}), hljs.initHighlightingOnLoad(), $(function() {
    _.contains([ "/careers.htm", "/careers/" ], window.location.pathname) && (Grnhse.Settings.scrollOnLoad = !1,
        router = new Router(), app = new App(), router.when({
        test: function(t) {
            return !t[0];
        },
        render: function(t) {
            app.index(t);
        }
    }), router.when({
        test: function(t) {
            return !!t[0] && "#" != t[0];
        },
        render: function(t) {
            redirects = {
                mp0hja6: "53525",
                mp0huy9: "53536",
                mp0huew: "53541",
                mp0lky: "58071",
                mp0hxo6: "58066"
            }, (redirectId = redirects[t[0]]) ? id = redirectId : id = t[0], app.post(id);
        }
    }), router.ready());
}), Router.prototype.goTo = function(t) {
    for (var e = 0; e < this.routes.length; e++) {
        var n = this.routes[e];
        if (n.test(t)) {
            n.render(t);
            break;
        }
    }
}, Router.prototype.when = function(t) {
    this.routes.push({
        test: t.test,
        render: t.render
    });
}, Router.prototype.ready = function() {
    _this = this, riot.route.exec(function() {
        _this.goTo(arguments);
    });
}, Greenhouse.prototype.rootUrl = "#",
    Greenhouse.prototype.departments = function(t) {
        $.ajax({
            url: this.rootUrl + "/departments",
            dataType: "jsonp"
        }).then(function(e) {
            departments = _.filter(e.departments, function(t) {
                return 0 != t.jobs.length;
            }), t(departments);
        });
    }, Greenhouse.prototype.get = function(t, e) {
    $.ajax({
        url: this.rootUrl + "/job",
        dataType: "jsonp",
        data: {
            id: t
        }
    }).then(e);
}, App.prototype.index = function(t) {
    this.api.departments(function(t) {
        $(".post").empty(), $(".application-form").empty(), $("#grnhse_app").empty(), riot.mountTo($(".postings")[0], "postings", {
            departments: t
        });
    });
}, App.prototype.post = function(t) {
    var e = function(t) {
        $(".postings").empty(), $(".post").empty(), $(".application-form").empty(), $("#grnhse_app").empty(),
            riot.mountTo($(".post")[0], "post", t), $("html, body").animate({
            scrollTop: $(".post").offset().top - 100
        }, 500);
    }, n = {
        api: this.api
    };
    this.api.get(t, function(t) {
        n.post = t, e(n);
    });
}, function() {
    "use strict";
    !function() {
        for (var t = 0, e = [ "ms", "moz", "webkit", "o" ], n = 0; n < e.length && !window.requestAnimationFrame; ++n) window.requestAnimationFrame = window[e[n] + "RequestAnimationFrame"],
            window.cancelAnimationFrame = window[e[n] + "CancelAnimationFrame"] || window[e[n] + "CancelRequestAnimationFrame"];
        window.requestAnimationFrame || (window.requestAnimationFrame = function(e, n) {
            var i = new Date().getTime(), r = Math.max(0, 16 - (i - t)), o = window.setTimeout(function() {
                e(i + r);
            }, r);
            return t = i + r, o;
        }), window.cancelAnimationFrame || (window.cancelAnimationFrame = function(t) {
            clearTimeout(t);
        });
    }();
    var t = function() {
        var t, e, /* n = '<% _.each(department, function(member, index) { %><li class="team__member"><div class="team__member__photo"><img src="<%= member.photo %>" alt=""></div><div class="team__member__info"><div class="info-container"><% if (member.linkedin) { %><a href="<%= member.linkedin %>" class="social-link" target="_blank"><img src="/assets/img/landing/linkdin.svg" alt=""/></a><% } %><% if (member.twitter) { %><a href="<%= member.twitter %>" class="social-link" target="_blank"><img src="/assets/img/landing/twitter.svg" alt="" /></a><% } %><h3><%= member.name %></h3><h4><%= member.title %></h4></div></div></li><% });%>', i = function(t) {
            e = t, $.getJSON("/assets/json/team.json").then(r), e.on("click", ".department-list li", s),
                e.on("click", ".team__member__info", function(t) {
                    var n = $(this);
                    n.hasClass("active") ? n.removeClass("active") : (e.find(".team__member__info.active").removeClass("active"),
                        n.addClass("active"));
                }), o();
        }, r = function(e) {
            t = e, _.each(t, function(t) {
                t.photo = a(t), $("<img />").attr("src", t.photo);
            }), t = _.sortBy(t, "name"), c(!0, "Leadership", {
                x: 0,
                y: 0
            }, 1.4);
        }, o = function() {
            $(".advisors .advisor").each(function(t) {
                $(this).css({
                    opacity: 0,
                    transition: "all 0.2s ease " + .18 * t + "s",
                    transform: "translateY(-10px)"
                });
            }), $(".advisors").hide().insertAfter(".team");
        }, s = function(t) {
            $(this).siblings().removeClass("active"), $(this).addClass("active");
            var n = {
                x: 0,
                y: 0
            }, i = $(this).data("department");
            "Advisors" === i ? c(!1, i, n, 2, function() {
                var t = e.find(".team");
                t.css("height", "");
                var n = t.height();
                t.html(""), $(".advisors").appendTo(".team").show();
                var i = t.height();
                $(".advisors .advisor").css({
                    opacity: 1,
                    transform: "translateY(0)"
                }), t.css("height", n).transition({
                    height: i
                }, function() {
                    t.css("height", "");
                });
            }) : $(".advisors").is(":visible") ? c(!0, i, n, 2) : c(!1, i, n, 2, function() {
                c(!0, i, n, 2);
            });
        }, a = function(t) {
            return "/assets/img/about/" + t.department.replace("/", ":") + "/" + t.name.replace(/\s/g, "-") + ".JPG";
        }, */u = {}, c = function(i, r, s, c, l) {
            var h, c = "undefined" == typeof c ? 1.4 : c, f = e.find(".team"), d = _.filter(t, function(t) {
                return t.department === r;
            }), p = _.template(n, _.extend({
                department: d
            }, {
                getPhotoPath: a
            })), m = {
                hidden: {
                    opacity: 0
                },
                visible: {
                    opacity: 1
                }
            }, g = i ? m.hidden : m.visible, v = i ? m.visible : m.hidden;
            if (i) {
                f.css("height", "");
                var y = f.height();
                o(), f.html(p);
                var b = e.find(".team__member");
                b.css(g), h = Math.sqrt(f.width() * f.width() + f.height() * f.height());
                var x = f.height();
                f.css("height", y).transition({
                    height: x
                }, function() {
                    f.css("height", "");
                });
            } else {
                var b = e.find(".team__member");
                b.css(g), h = Math.sqrt(f.width() * f.width() + f.height() * f.height());
            }
            var w = b.map(function(t) {
                var e = $(this), n = {
                    x: e.position().left + e.width() / 2,
                    y: e.position().top + e.height() / 2
                }, i = s.x - n.x, r = s.y - n.y, o = Math.sqrt(i * i + r * r);
                return {
                    $el: e,
                    distance: o
                };
            }).get();
            u.interval && clearInterval(u.interval), u.start = new Date().getTime(), u.interval = setInterval(function() {
                var t = new Date().getTime(), e = (t - u.start) * c;
                _.each(w, function(t) {
                    !t.tapped && e > t.distance && (t.$el.transition(v), t.tapped = !0);
                }), e > h && (clearInterval(u.interval), u.interval = null, l && l());
            }, 30);
        };
        return {
            ini: i,
            toggleDepartment: c
        };
    }();
    $(function() {
        $("body").hasClass("about-page") && t.init($(".about__team"));
    });
}(), $(function() {
    var t = $("#canada-overlay.fixed-overlay").is(":visible");
    1 == t ? $("body").addClass("noscroll") : 0 == t && $("body").removeClass("noscroll"),
        $(".fixed-overlay .close-btn").click(function(t) {
            $(this).closest(".fixed-overlay").addClass("hide"), $("body").removeClass("noscroll");
        });
}), $(function() {
    $.getScript("https://f.vimeocdn.com/js/froogaloop2.min.js", function() {
        player = $f($("#vimeoplayer")[0]), $(".js-module-video-play").click(function() {
            $(".js-module-video").show(), player.api("seekTo", 0), player.api("play"), $("body").addClass("noscroll"),
                $("#broll-video")[0].pause(), window.analytics.track("Viewed Video");
        }), $(".js-module-video").click(function() {
            $(".js-module-video").hide(), player.api("pause"), $("body").removeClass("noscroll"),
                $("#broll-video")[0].play();
        }), $(".js-module-video-window").click(function(t) {
            t.stopPropagation();
        }), $(document).keyup(function(t) {
            27 == t.keyCode && ($(".js-module-video").hide(), player.api("pause"), $("body").removeClass("noscroll"),
                $("#broll-video")[0].play());
        });
    });
}), riot.tag("application-form", '<form onsubmit="{ this.submit }"> <div class="bl-input-group" style="display:none"> <input name="id" value="{ postId }"> </div> <div class="bl-input-group" each="{ opts.questions }"> <label class="bl-label"> {label} <span class="bl-input-group__is-required" if="{ required && !parent.errorMessages[name]}">*</span> </label> <span class="bl-input-group__error-message" if="{ parent.errorMessages[name] }">*{ parent.errorMessages[name].join(\', \') }</span> <input class="bl-input" name="{ fields[0].name }" if="{ fields[0].type == \'input_text\' }" type="text"> <textarea class="bl-textarea" name="{ fields[0].name }" if="{ fields[0].type == \'textarea\' }" rows="9" ></textarea> <select class="bl-select" name="{ fields[0].name }" if="{ fields[0].type == \'multi_value_single_select\' }"> <option each="{ fields[0].values }" value="{ value }"> { label } </option> </select> <input class="bl-input" name="{ fields[0].name }" if="{ fields[0].type == \'input_file\' }" type="file"> </div> <div class="bl-input-group"> <button type="submit" class="bl-btn-submit { (formStatus==\'loading\')? \'loading\': \'\' }" __disabled="{ formStatus != \'ready\' }"> { buttonText[formStatus] } </button> <span class="bl-input-group__error-message" if="{ formInvalid }"> <br>*There were errors in your application. Please review and try again. </span> </div> </form>', function(t) {
    this.errorMessages = {}, this.formInvalid = !1, this.formStatus = "ready", this.buttonText = {
        ready: "Submit",
        submitting: "Processing...",
        submitted: "Thanks!"
    }, this.postId = t.post_id, this.api = t.api, this.submit = function(t) {
        var e = this;
        data = new FormData(t.target), this.formStatus = "submitting", this.api.post(this.postId, data, function(t) {
            console.log(t), e.update({
                submitted: !0,
                errorMessages: {},
                formInvalid: !1,
                formStatus: "submitted"
            });
        }, function(t) {
            e.update({
                errorMessages: t,
                formInvalid: !0,
                formStatus: "ready"
            });
        });
    }, this.on("mount update", function() {
        0 != this.root.children.length && _.chain(this.root.children[0].children).map(function(t) {
            return input = _.filter(t.children, function(t) {
                return "none" == $(t).css("display") && "SPAN" != $(t).prop("tagName");
            });
        }).flatten().forEach(function(t) {
            $(t).remove();
        });
    });
}), riot.tag("postings", '<h2>bangbangzhang</h2> <div each="{opts.departments}"> <h3>{ name }</h3> <ul class="posting-list"> <li each="{ jobs }" class="posting"> <a href="#" onclick="{parent.parent.select}" title="{title}"> <span class="posting__title"> {title} </span> <span class="posting__location"> {location.name} </span> </a> </li> </ul> </div>', function(t) {
    this.select = function() {
        riot.route(String(this.id));
    };
}), riot.tag("post", '<a href="#" class="index-link" onclick="{ this.returnToIndex }"><span class="back-arrow ss-navigateleft"></span>Back To All Posts</a> <h2 class="title"> {opts.post.title} <span class="location"> ({opts.post.location.name}) </span> </h2> <div class="post-body"> </div> <a href="#" class="bl-btn-submit" onclick="{ this.apply }" if="{ !this.showForm }"> Apply now </a> <div class="{ form_is_visible: this.showForm, form_not_visible: !this.showForm }"> <div id="grnhse_app"></div> </div>', function(t) {
    function e(t) {
        return $("<div/>").html(t).text();
    }
    this.showForm = !1, this.returnToIndex = function(t) {
        t.preventDefault(), riot.route("/");
    }, this.apply = function() {
        this.showForm = !0, $("html, body").animate({
            scrollTop: $("#grnhse_app").offset().top - 150
        }, 500);
    }, this.on("mount", function() {
        Grnhse.Iframe.load(t.post.id), $(".post-body").html(e(t.post.content));
    });
});