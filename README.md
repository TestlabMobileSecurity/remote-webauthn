# Remote WebAuthn
*Remote WebAuthn* is a WebSocket-based open-source protocol that allows wireless FIDO2 authentication on remote devices. In contrast to competing approaches, *Remote WebAuthn* does not require prior pairing and is hence also suited for temporary applications like kiosk terminals.

This repository contains a reference implementation following the architecture proposed in the paper "[Remote WebAuthn: FIDO2 Authentication for Less Accessible Devices](https://www.scitepress.org/PublishedPapers/2021/101927/101927.pdf)". The initial code version was implemented as part of the bachelor's thesis "FIDO2-based Remote Authentication with Mobile Devices" at Fraunhofer SIT.

## Citation
```
@inproceedings{wagner2021remote,
  title={Remote WebAuthn: FIDO2 Authentication for Less Accessible Devices.},
  author={Wagner, Paul and Heid, Kris and Heider, Jens},
  booktitle={ICISSP},
  pages={368--375},
  year={2021}
}
```

## Component Overview
| Component              | Language            | Subdirectory              |
|------------------------|---------------------|---------------------------|
| *Proxy Server*         | Go                  | `./server`                |
| *Relying Party Client* | TypeScript          | `./client/lib/ts`         |
| *Authenticator Client* | Java / Kotlin       | `./authenticator/android` |

## Getting Started
For getting started, first a publicly accessible *Proxy Server* allowing communication between *Relying Party Client* and *Authenticator Client* must be set up. Then, both clients can be installed to start an exemplary authentication process.

### Proxy Server
The *Proxy Server* is located inside `./server` and can be set up using the included Makefile:

1. `cd server`
2. `make`

After booting the server up using `./rwa_server`, it listens on port `8081` for all incoming packages by default.

### Relying Party Client
`./client/lib/ts` contains the pre-transpiled *Relying Party* JavaScript-library. Alternatively, the library can be self-transpiled using [TypeScript](https://www.typescriptlang.org) and [Webpack](https://webpack.js.org).

### Authenticator Client
The included *Authenticator Client* for Android is located under `./authenticator/android`. Building the project can either be done by importing the project into [Android Studio](https://developer.android.com/studio) or by using [Gradle](https://gradle.org) directly.

The app itself uses deep links for authentication initialization, therefore an additional QR code scanner like [ZXing](https://github.com/zxing/zxing) must be installed first. Please keep in mind, that this exemplary implementation relies on [WebAuthnKit](https://github.com/lyokato/WebAuthnKit-Android) as authenticator and hence may not be used in production environments.
