//
// Autogenerated by Thrift Compiler (0.9.3)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//


//HELPER FUNCTIONS AND STRUCTURES

CacheService_refreshCacheIfEnabled_args = function(args) {
};
CacheService_refreshCacheIfEnabled_args.prototype = {};
CacheService_refreshCacheIfEnabled_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_refreshCacheIfEnabled_args.prototype.write = function(output) {
  output.writeStructBegin('CacheService_refreshCacheIfEnabled_args');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheService_refreshCacheIfEnabled_result = function(args) {
};
CacheService_refreshCacheIfEnabled_result.prototype = {};
CacheService_refreshCacheIfEnabled_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_refreshCacheIfEnabled_result.prototype.write = function(output) {
  output.writeStructBegin('CacheService_refreshCacheIfEnabled_result');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheService_refreshCacheForUserIfEnabled_args = function(args) {
  this.username = null;
  if (args) {
    if (args.username !== undefined && args.username !== null) {
      this.username = args.username;
    }
  }
};
CacheService_refreshCacheForUserIfEnabled_args.prototype = {};
CacheService_refreshCacheForUserIfEnabled_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRING) {
        this.username = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_refreshCacheForUserIfEnabled_args.prototype.write = function(output) {
  output.writeStructBegin('CacheService_refreshCacheForUserIfEnabled_args');
  if (this.username !== null && this.username !== undefined) {
    output.writeFieldBegin('username', Thrift.Type.STRING, 1);
    output.writeString(this.username);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheService_refreshCacheForUserIfEnabled_result = function(args) {
};
CacheService_refreshCacheForUserIfEnabled_result.prototype = {};
CacheService_refreshCacheForUserIfEnabled_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_refreshCacheForUserIfEnabled_result.prototype.write = function(output) {
  output.writeStructBegin('CacheService_refreshCacheForUserIfEnabled_result');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheService_isCacheEnabled_args = function(args) {
};
CacheService_isCacheEnabled_args.prototype = {};
CacheService_isCacheEnabled_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_isCacheEnabled_args.prototype.write = function(output) {
  output.writeStructBegin('CacheService_isCacheEnabled_args');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheService_isCacheEnabled_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined && args.success !== null) {
      this.success = args.success;
    }
  }
};
CacheService_isCacheEnabled_result.prototype = {};
CacheService_isCacheEnabled_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.BOOL) {
        this.success = input.readBool().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_isCacheEnabled_result.prototype.write = function(output) {
  output.writeStructBegin('CacheService_isCacheEnabled_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.BOOL, 0);
    output.writeBool(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheService_setCacheEnabled_args = function(args) {
  this.enable = null;
  if (args) {
    if (args.enable !== undefined && args.enable !== null) {
      this.enable = args.enable;
    }
  }
};
CacheService_setCacheEnabled_args.prototype = {};
CacheService_setCacheEnabled_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.BOOL) {
        this.enable = input.readBool().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_setCacheEnabled_args.prototype.write = function(output) {
  output.writeStructBegin('CacheService_setCacheEnabled_args');
  if (this.enable !== null && this.enable !== undefined) {
    output.writeFieldBegin('enable', Thrift.Type.BOOL, 1);
    output.writeBool(this.enable);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheService_setCacheEnabled_result = function(args) {
};
CacheService_setCacheEnabled_result.prototype = {};
CacheService_setCacheEnabled_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_setCacheEnabled_result.prototype.write = function(output) {
  output.writeStructBegin('CacheService_setCacheEnabled_result');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

CacheServiceClient = function(input, output) {
    this.input = input;
    this.output = (!output) ? input : output;
    this.seqid = 0;
};
CacheServiceClient.prototype = {};
CacheServiceClient.prototype.refreshCacheIfEnabled = function(callback) {
  this.send_refreshCacheIfEnabled(callback); 
  if (!callback) {
  this.recv_refreshCacheIfEnabled();
  }
};

CacheServiceClient.prototype.send_refreshCacheIfEnabled = function(callback) {
  this.output.writeMessageBegin('refreshCacheIfEnabled', Thrift.MessageType.CALL, this.seqid);
  var args = new CacheService_refreshCacheIfEnabled_args();
  args.write(this.output);
  this.output.writeMessageEnd();
  if (callback) {
    var self = this;
    this.output.getTransport().flush(true, function() {
      var result = null;
      try {
        result = self.recv_refreshCacheIfEnabled();
      } catch (e) {
        result = e;
      }
      callback(result);
    });
  } else {
    return this.output.getTransport().flush();
  }
};

CacheServiceClient.prototype.recv_refreshCacheIfEnabled = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new CacheService_refreshCacheIfEnabled_result();
  result.read(this.input);
  this.input.readMessageEnd();

  return;
};
CacheServiceClient.prototype.refreshCacheForUserIfEnabled = function(username, callback) {
  this.send_refreshCacheForUserIfEnabled(username, callback); 
  if (!callback) {
  this.recv_refreshCacheForUserIfEnabled();
  }
};

CacheServiceClient.prototype.send_refreshCacheForUserIfEnabled = function(username, callback) {
  this.output.writeMessageBegin('refreshCacheForUserIfEnabled', Thrift.MessageType.CALL, this.seqid);
  var args = new CacheService_refreshCacheForUserIfEnabled_args();
  args.username = username;
  args.write(this.output);
  this.output.writeMessageEnd();
  if (callback) {
    var self = this;
    this.output.getTransport().flush(true, function() {
      var result = null;
      try {
        result = self.recv_refreshCacheForUserIfEnabled();
      } catch (e) {
        result = e;
      }
      callback(result);
    });
  } else {
    return this.output.getTransport().flush();
  }
};

CacheServiceClient.prototype.recv_refreshCacheForUserIfEnabled = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new CacheService_refreshCacheForUserIfEnabled_result();
  result.read(this.input);
  this.input.readMessageEnd();

  return;
};
CacheServiceClient.prototype.isCacheEnabled = function(callback) {
  this.send_isCacheEnabled(callback); 
  if (!callback) {
    return this.recv_isCacheEnabled();
  }
};

CacheServiceClient.prototype.send_isCacheEnabled = function(callback) {
  this.output.writeMessageBegin('isCacheEnabled', Thrift.MessageType.CALL, this.seqid);
  var args = new CacheService_isCacheEnabled_args();
  args.write(this.output);
  this.output.writeMessageEnd();
  if (callback) {
    var self = this;
    this.output.getTransport().flush(true, function() {
      var result = null;
      try {
        result = self.recv_isCacheEnabled();
      } catch (e) {
        result = e;
      }
      callback(result);
    });
  } else {
    return this.output.getTransport().flush();
  }
};

CacheServiceClient.prototype.recv_isCacheEnabled = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new CacheService_isCacheEnabled_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'isCacheEnabled failed: unknown result';
};
CacheServiceClient.prototype.setCacheEnabled = function(enable, callback) {
  this.send_setCacheEnabled(enable, callback); 
  if (!callback) {
  this.recv_setCacheEnabled();
  }
};

CacheServiceClient.prototype.send_setCacheEnabled = function(enable, callback) {
  this.output.writeMessageBegin('setCacheEnabled', Thrift.MessageType.CALL, this.seqid);
  var args = new CacheService_setCacheEnabled_args();
  args.enable = enable;
  args.write(this.output);
  this.output.writeMessageEnd();
  if (callback) {
    var self = this;
    this.output.getTransport().flush(true, function() {
      var result = null;
      try {
        result = self.recv_setCacheEnabled();
      } catch (e) {
        result = e;
      }
      callback(result);
    });
  } else {
    return this.output.getTransport().flush();
  }
};

CacheServiceClient.prototype.recv_setCacheEnabled = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new CacheService_setCacheEnabled_result();
  result.read(this.input);
  this.input.readMessageEnd();

  return;
};
