BEGIN {
  while (getline line <"doc/user-doc.latte")
    if (line == "@table@")
      break;
    else
      print line;
  print "{\\table3";
  print " {{\\b Feature} {\\b Syntax} {\\b Examples}}";
}

{
  f1 = trim(substr($0, 1, 24));
  f2 = trim(substr($0, 25, 15));
  f3 = trim(substr($0, 41));
  if (f1 == "" && f2 == "") {
    keep3 = keep3 "{\\dt " code(f3) "}";
  } else {
    flush();
    keep1 = "{" f1 "}";
    keep2 = code(f2);
    keep3 = "{\\dt " code(f3) "}";
  }
}

END {
  flush();
  print " }";
  while (getline line <"doc/user-doc.latte")
    print line;
}


function trim(s)
{
  sub(/[ \t]*$/, "", s);
  return s;
}

function code(s)
{
  if (s ~ /code/)
    return "{" s "}";
  return "{\\code " s "}";
}

function flush()
{
  if (keep1)
    printf("{%s %s {\\dl %s}}\n", keep1, keep2, keep3);
}
