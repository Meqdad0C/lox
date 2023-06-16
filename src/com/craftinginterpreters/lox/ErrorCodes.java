package com.craftinginterpreters.lox;
/* https://man.freebsd.org/cgi/man.cgi?query=sysexits&manpath=FreeBSD+4.3-RELEASE */
interface ErrorCodes {
    int EX_OK          = 0   ;    /* successful termination */
    int EX__BASE       = 64  ;    /* base value for error messages */
    int EX_USAGE       = 64  ;    /* command line usage error */
    int EX_DATAERR     = 65  ;    /* data format error */
    int EX_NOINPUT     = 66  ;    /* cannot open input */
    int EX_NOUSER      = 67  ;    /* addressee unknown */
    int EX_NOHOST      = 68  ;    /* host name unknown */
    int EX_UNAVAILABLE = 69  ;    /* service unavailable */
    int EX_SOFTWARE    = 70  ;    /* internal software error */
    int EX_OSERR       = 71  ;    /* system error (e.g., can't fork) */
    int EX_OSFILE      = 72  ;    /* critical OS file missing */
    int EX_CANTCREAT   = 73  ;    /* can't create (user) output file */
    int EX_IOERR       = 74  ;    /* input/output error */
    int EX_TEMPFAIL    = 75  ;    /* temp failure; user is invited to retry */
    int EX_PROTOCOL    = 76  ;    /* remote error in protocol */
    int EX_NOPERM      = 77  ;    /* permission denied */
    int EX_CONFIG      = 78  ;    /* configuration error */
}
