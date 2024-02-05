{
  description = "Calgary Transit GTFS Data processor and JSON API";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs = { self, nixpkgs }: {
    devShell.x86_64-linux = with nixpkgs.legacyPackages.x86_64-linux; mkShell {
      buildInputs = [ 
        jdk11
        gradle 
      ];
    };
  };
}

